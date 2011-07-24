from cloudfusion.store.dropbox.file_decorator import NonclosingFile
from cloudfusion.store.store import NoSuchFilesytemObjectError,\
    AlreadyExistsError, StoreAccessError, StoreAutorizationError
import os, sys, stat,  time
from errno import *
from cloudfusion.fuse import FUSE, FuseOSError, Operations, LoggingMixIn
import tempfile

# Specify what Fuse API use: 0.2
#fuse.fuse_python_api = (0, 2)

def zstat():
    now = time.time()
    st = {}
    st['st_mode'] = 0
    st['st_ino']  = 0
    st['st_dev']  = 0
    st['st_nlink']= 1
    st['st_uid']  = os.getuid()
    st['st_gid']  = os.getgid()
    st['st_size'] = 0
    st['st_atime']= now
    st['st_mtime']= now
    st['st_ctime']= now
    return st

class PyFuseBox(Operations):
    def __init__(self, path, store):
        self.root = path
        self.temp_file = {}
        self.read_temp_file = {}
        self.store = store
        self.f = open('fuselog', 'w')

        #store.store_file(file, root_dir)
    def getattr(self, path, fh=None):
        #FuseOSError(EPERM)#zugriff nicht moeglich
        #self.f.write( "getattr "+path+"\n")
        st = zstat()
        try:
            metadata = self.store._get_metadata(path)
        except: 
            raise FuseOSError(ENOENT)
        st['st_atime']= metadata['modified']
        st['st_mtime']= metadata['modified']
        st['st_ctime']= metadata['modified']
        if metadata['is_dir']:
            self.f.write( " isDir\n")
            st['st_mode'] = 0777 | stat.S_IFDIR
            st['st_nlink']=2
            st['st_size'] = 1
        else:
            self.f.write( " isFile\n")
            st['st_mode'] = 0777 | stat.S_IFREG
            st['st_size'] = metadata['bytes']
        st['st_blocks'] = (int) ((st['st_size'] + 4095L) / 4096L);
        return st
    
    def open(self, path, flags):
        """self.f.write( "open "+path+"\n")
        temp_file = tempfile.SpooledTemporaryFile()
        #self.temp_file = tempfile.SpooledTemporaryFile()
        if self.store.exists(path):
            file = self.store.get_file(path)
            self.temp_file.write(file)
            self.temp_file.seek(0)
        else:
            self.store.store_fileobject(self.temp_file,path)
        #self.store.store_fileobject(self.temp_file,path)
        self.store.store_fileobject(temp_file,path)"""
        return 0
    
    def truncate(self, path, length, fh=None):
        self.f.write( "truncate %s to %s\n" % (path, length))
        self.store.delete(path)
        temp_file = tempfile.SpooledTemporaryFile()
        self.store.store_fileobject(temp_file, path)
        try:
            self.store.flush()
        except:
            pass;
        return 0
    
    def rmdir(self, path):
        #raise FuseOSError(EPERM)#nicht gefunden
        self.f.write( "rmdir %s\n" % (path))
        try:
            self.store.delete(path)
        except NoSuchFilesytemObjectError:
            raise FuseOSError(ENOENT)
        except StoreAccessError:
            raise FuseOSError(EIO)
        except StoreAutorizationError:
            raise FuseOSError(EACCES) 
        
    def mkdir(self, path, mode):
        #raise FuseOSError(EACCES)#keine berechtigung
        #raise FuseOSError(EPERM) #operation ist nicht moeglich
        self.f.write( "mkdir %s with mode: %s\n" % (path, str(mode)))
        try:
            self.store.create_directory(path)
        except NoSuchFilesytemObjectError:
            raise FuseOSError(ENOENT)
        except AlreadyExistsError:
            raise FuseOSError(EEXIST)
        except StoreAccessError:
            raise FuseOSError(EIO)
        except StoreAutorizationError:
            raise FuseOSError(EACCES) #keine Berechtigung

    def statfs(self, path):
        """ This implementation should be looked at by a linux guru, since I have little experience concerning filesystems. """
        ret = {}
        ret['f_bsize'] = 4096 #Preferred file system block size.
        ret['f_bavail'] = int( self.store.get_free_space() / ret['f_bsize'] ) #Free blocks available to non-super user.
        ret['f_bfree'] = int( self.store.get_free_space() / ret['f_bsize'] ) #Total number of free blocks.
        ret['f_blocks'] = int( self.store.get_overall_space() / ret['f_bsize'] ) #Total number of blocks in the filesystem.
        ret['f_favail'] = 810280 #Free nodes available to non-super user -- not sure about this
        ret['f_ffree'] = ret['f_favail'] #Total number of free file nodes.
        ret['f_files'] = 810280 #Total number of file nodes -- not sure about this
        ret['f_flag'] = 4096 #Flags. System dependent: see statvfs() man page.
        ret['f_frsize'] = 4096 #Fundamental file system block size.
        ret['f_namemax'] = 255 #Maximum file name length.
        return ret
    
    def rename(self, old, new):
        self.f.write( "rename %s to %s\n" % (old, new))
        self.store.move(old, new)

    def create(self, path, mode):
        self.f.write( "create %s with mode %s\n" % (path, str(mode)))
        temp_file = tempfile.SpooledTemporaryFile()
        self.store.store_fileobject(temp_file, path)
        try:
            self.store.flush()
        except:
            pass;
        return 0
        """       self.files[path] = dict(st_mode=(S_IFREG | mode), st_nlink=1,
            st_size=0, st_ctime=time(), st_mtime=time(), st_atime=time())
        self.fd += 1
        return self.fd
    def truncate(self, path, length, fh=None):
        self.data[path] = self.data[path][:length]
        self.files[path]['st_size'] = length"""
    
    def unlink(self, path):
        self.f.write( "unlink %s\n" % path)
        self.store.delete(path)

    def read(self, path, size, offset, fh):
        self.f.write( "read %s bytes from %s at %s - fh %s\n" % (size, path, offset, fh))
        if not path in self.read_temp_file:
            self.read_temp_file[path] = tempfile.SpooledTemporaryFile()
            file = self.store.get_file(path)
            self.read_temp_file[path].write(file)
        self.read_temp_file[path].seek(0)
        file =  self.read_temp_file[path].read()
        #file.seek(offset)
        return  file[offset: offset+size]

    def write(self, path, buf, offset, fh):
        self.f.write( "write %s ... starting with %s at %s - fh: %s\n" % (path, buf[0:10], offset, fh))
        if not path in self.temp_file:
            self.temp_file[path] = tempfile.SpooledTemporaryFile()
            file = self.store.get_file(path)
            self.temp_file[path].write(file)
        self.temp_file[path].seek(offset)
        self.temp_file[path].write(buf)
        self.temp_file[path].seek(0)
        #self.store.store_fileobject(self.temp_file[path],path)
        return len(buf)
    
    def flush(self, path, fh):
        self.f.write( "flush %s - fh: %s\n" % (path, fh))
        if path in self.temp_file: #after writes
            self.store.store_fileobject(self.temp_file[path], path)
            try:
                self.store.flush()
            except:
                pass;
        return 0
    
    def release(self, path, fh):
        self.f.write( "release %s - fh: %s\n" % (path, fh))
        if path in self.temp_file: #after writes
            self.temp_file[path].close()
            del self.temp_file[path]
        if path in self.read_temp_file:
            self.read_temp_file[path].close()
            del self.read_temp_file[path]
             
        #self.temp_file.close()
        return 0 #UnicodeEncodeError: 'ascii' codec can't encode character u'\xed' in position 20: ordinal not in range(128)    
       
    def readdir(self, path, fh):
        self.f.write( "readdir "+path+"\n")
        directories = self.store.get_directory_listing(path)
        #self.f.write( "readdir -> "+str(directories)+"\n")
        file_objects = [".", ".."]
        for file_object in directories:
            if file_object != "/":
                file_object = os.path.basename(file_object.encode('utf8'))
                file_objects.append( file_object )
        return file_objects;



