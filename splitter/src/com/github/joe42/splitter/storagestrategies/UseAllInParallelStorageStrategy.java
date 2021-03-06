package com.github.joe42.splitter.storagestrategies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.HashSet;

import org.apache.log4j.Logger;


import com.github.joe42.splitter.backend.BackendService;
import com.github.joe42.splitter.backend.BackendServices;
import com.github.joe42.splitter.util.math.SetUtil;

/**
 * Uses all potential storage directories.
 */
public class UseAllInParallelStorageStrategy  implements StorageStrategy, Observer {
	private static final Logger log = Logger.getLogger("UseAllInParallelStorageStrategy");
	protected List<String> potentialStorageDirectories;
	protected int redundancy;
	protected long filesize;
	protected BackendServices services;
	
	
	/**
	 * Creates a UseAllInParallelStorageStrategy object using all potentialStorageDirectories in parallel with a redundancy of 50%.
	 * @param storageServices
	 */
	public UseAllInParallelStorageStrategy(BackendServices storageServices){
		this.services = storageServices;
		storageServices.addObserver(this);
		update();
		redundancy = 50;
	}
	
	/**
	 * Put the storage strategy into a consistent state. 
	 * Should be called after a storage service has changed.
	 */
	public void update() {
		potentialStorageDirectories = new ArrayList<String>();
		for(BackendService storageService: services.getFrontEndStorageServices()){
			for(int i=0; i<storageService.getNrOfFilePartsToStore();i++){
				potentialStorageDirectories.add(storageService.getDataDirPath());
			}
		}
		System.out.println("update: "+potentialStorageDirectories);
	}

	/**
	 * @return the availability of the file stored in percent according to this storage strategy and the storage services' availability
	 */
	@Override
	public double getStorageAvailability(){
		double availability = 0;
		if(potentialStorageDirectories.size() == 0) {
			return 0;
		}		
		Set<BackendService> storageServices = new HashSet<BackendService>(services.getFrontEndStorageServices());
		for(Set<BackendService> storageServiceCombination: SetUtil.powerSet(storageServices)){
			if(BackendServices.getNrOfFilePartsOfCombination(storageServiceCombination) >= getNrOfFilePartsNeededToReconstructFile()){
				log.debug("combination:"+storageServiceCombination+ " fileparts:"+BackendServices.getNrOfFilePartsOfCombination(storageServiceCombination)+ " needed:"+getNrOfFilePartsNeededToReconstructFile());
				availability += BackendServices.getExclusiveAvailabilityOfStorageCombination(storageServiceCombination, new HashSet<BackendService>(services.getFrontEndStorageServices()));
			}
		}
		log.debug("storage availability:"+availability);
		return availability;
	}

	private int getNrOfFilePartsNeededToReconstructFile() {
		return potentialStorageDirectories.size()-getNrOfRedundantFragments();
	}

	public void setStorageServices(BackendServices services){
		this.services.deleteObserver(this);
		this.services = services;
		services.addObserver(this);
	}
	
	public BackendServices getStorageServices(){
		return services;
	}
	
	/**
	 * Set the redundancy in percent from 0 to 100. 
	 * See {@link #getNrOfRedundantFragments() getNrOfRedundantFragments()} for the exact formula details.
	 * @param redundancy
	 */
	public void setRedundancy(int redundancy){
		this.redundancy = redundancy;
	}
	
	public int getRedundancy(){
		return redundancy;
	}

	public void setFileSize(long filesize){
		this.filesize = filesize;
	}
	
	public long getFileSize(){
		return filesize;
	}
	
	/**
	 * @return false
	 */
	public boolean changeToCurrentStrategy(StorageStrategy other){
		return false;
	}

	/**
	 * Get the directories to store the file fragments to
	 * Returns the storage directories for all storage services, as often as specified by each service's {@link BackendServices#getNrOfFilePartsToStore() getNrOfFilePartsToStore()} 
	 * @return a list of directory paths 
	 */
	@Override
	public List<String> getFragmentDirectories() {
		return potentialStorageDirectories;
	}

	/**
	 * Get the number of fragments that can be lost or corrupted before the file cannot be restored. 
	 * Returns the number of potential stores minus one multiplied by the redundancy. If  the resulting number has a fractional part, the next lower integer is returned. 
	 * For instance, 8 stores and a redundancy of 50% (x = (8-1)*0.5 = 3.5) result in a return value of 3 redundant fragments.  
	 * @return the number of redundant fragments 
	 */
	@Override
	public int getNrOfRedundantFragments() {
		int nrOfFileStores =  potentialStorageDirectories.size();
		int nrOfRedundantFragments = (int) ((nrOfFileStores-1) * (redundancy /100f));
		return nrOfRedundantFragments;
	}

	/**
	 * Get the number of file fragments that must be stored successfully.
	 * The number equals the number of all fragments.
	 * @return the number of file fragments that must be stored
	 */
	@Override
	public int getNrOfRequiredSuccessfullyStoredFragments() {
		return potentialStorageDirectories.size(); 
	}

	
	/**
	 * Put the storage strategy into a consistent state after this strategy's services have been changed
	 */
	@Override
	public void update(Observable storageServices, Object arg1) {
		update(); // update possible changes to services 
	}
}
