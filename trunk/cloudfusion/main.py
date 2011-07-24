'''
Created on 12.05.2011

@author: joe
'''
from cloudfusion.pyfusebox.pyfusebox import PyFuseBox
from cloudfusion.fuse import FUSE
from cloudfusion.store.dropbox.dropbox_store import DropboxStore
from cloudfusion.store.sugarsync.sugarsync_store import SugarsyncStore
from cloudfusion.store.caching_store import CachingStore
import os, sys, stat,  time
from dropbox import auth
import getpass
from ConfigParser import SafeConfigParser

def get_password():
    print "Enter Password for the account:"
    password =  getpass.getpass()
    return password

def get_dropbox_config():
    return auth.Authenticator.load_config("cloudfusion/config/dropbox.ini")

def get_sugarsync_config():
    config = SafeConfigParser()
    config_file = open("cloudfusion/config/sugarsync.ini", "r")
    config.readfp(config_file)
    return dict(config.items('auth'))

def get_store(service, password=None):
    if password == None:
        password = get_password()
    if service.lower() == "sugarsync":
        config = get_sugarsync_config()
        config['password'] = password
        store = SugarsyncStore(config)
    else: # default
        config = get_dropbox_config()
        config['password'] = password
        store = DropboxStore(config)
    return store

def check_arguments(args):
    if not len(args) in [2,3,4,5]:
        print 'usage: %s mountpoint  [service] [service password] [cache]' % args[0]
        exit(1)

def main():
    check_arguments(sys.argv)
    service = "dropbox"
    password  = None
    if len(sys.argv) in [3,4,5]:
        service = sys.argv[2]
    if len(sys.argv) in [4,5]:
        password= sys.argv[3]
    store = get_store(service, password)
    if "cache" in sys.argv:
        store = CachingStore( store )
    fuse_operations = PyFuseBox(sys.argv[1], store)
    FUSE(fuse_operations, sys.argv[1], foreground=True, nothreads=True)
    
if __name__ == '__main__':
    main()