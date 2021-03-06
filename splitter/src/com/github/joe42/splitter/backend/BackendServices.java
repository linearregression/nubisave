package com.github.joe42.splitter.backend;

import java.io.File;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.HashSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.ini4j.Ini;

import com.github.joe42.splitter.util.file.FileUtil;

/**
 *Manages the storages used by NubiSave. The actual stores are instances of BackendService.
 */
public class BackendServices extends Observable { 
	private static final Logger log = Logger.getLogger("BackendServices");
	private Map<String, BackendService> services;
	
	public BackendServices(){
		this.services = new HashMap<String, BackendService>();
	}

	/**
	 * Return true iff o is an instance of BackendServices and maps the same service names to the same BackendService instances.
	 */
	@Override
	public boolean equals(Object o){
		if( ! (o instanceof BackendServices) ){
			return false;
		}
		return ((BackendServices)o).services.equals(services);
	}
	
	@Override
	public int hashCode(){
		return services.hashCode();
	}
	
	/**
	 * Adds a BackendService object
	 * @param serviceName the name of the service
	 * @param service the BackendService object
	 */
	public void add(String serviceName, BackendService service){
		services.put(serviceName, service);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Removes a service by its name
	 * @param serviceName the name of a BackendService
	 */
	public void remove(String serviceName){
		services.remove(serviceName);
		setChanged();
		notifyObservers();
	}

	/**
	 * Get a BackendService object 
	 * @param serviceName the name of the BackendService object
	 * @return a BackendService object with the specified name or null if the service is not managed by this BackendServices' instance
	 */
	public BackendService get(String serviceName){
		return services.get(serviceName);
	}

	/**
	 * Get a BackendService object 
	 * @param dataDirPath the data directory path of the BackendService object
	 * @return a BackendService object with the specified data directory path or null if the service is not managed by this BackendServices' instance
	 */
	public BackendService getServiceByDataDirPath(String dataDirPath){
		for(BackendService service: services.values()){
			if(service.getDataDirPath().equals(dataDirPath)){
				return service;
			}
		}
		return null;
	}
	
	/**
	 * Get the unique name of a service by its data directory
	 * @param dataDirPath the data directory path of the BackendService object
	 * @return the unique name of a service
	 */
	public static String getUniqueServiceNameByDataDirPath(String dataDirPath){
		return new File(dataDirPath).getParent();
	}
	
	/**
	 * @return the front end storage services' directory for data access
	 */
	public List<String> getDataDirPaths() {
		List<String> ret = new ArrayList<String>();
		for(BackendService service: getFrontEndStorageServices()){
				ret.add(service.getDataDirPath());
		}
		return ret;
	}

	/**
	 * @return the front end storage services
	 */
	public List<BackendService> getFrontEndStorageServices() {
		List<BackendService> storageServices = new ArrayList<BackendService>();
		for(BackendService service: services.values()){
			if(! service.isBackendModule()){
				storageServices.add(service);
			}
		}
		return storageServices;
	}
	
	/**
	 * @return list of all storage services' names
	 */
	public List<String> getStorageServicesNames() {
		List<String> storageServices = new ArrayList<String>(services.keySet());
		return storageServices;
	}

	/**
	 * @param storageServiceCombination
	 * @return number of file parts stored in storageServiceCombination for each stored file
	 */
	public static int getNrOfFilePartsOfCombination(
			Set<BackendService> storageServiceCombination) {
		int fileParts = 0;
		for(BackendService storageService: storageServiceCombination){
			fileParts += storageService.getNrOfFilePartsToStore();
		}
		return fileParts;
	}

	/**
	 * Get probability of only the storageServiceCombination being available while no other storage in superSet is available
	 * @param storageServiceCombination subset superSet 
	 * @param superSet combination of front end storage services
	 * @return probability for the storageServiceCombination being available exclusively
	 */
	@SuppressWarnings("unchecked")
	public static double getExclusiveAvailabilityOfStorageCombination(Set<BackendService> storageServiceCombination, Set<BackendService> superSet) {
		double exclusiveAvailability = 0, availability = 0, unavailability = 0;
		availability = getAvailabilityOfCombination(storageServiceCombination);
		log.debug("availability:"+availability);
		unavailability = getUnavailabilityOfCombination( new HashSet<BackendService>(CollectionUtils.disjunction(superSet, storageServiceCombination)) );
		exclusiveAvailability = availability;
		if(unavailability != 0){
			exclusiveAvailability = availability * unavailability;
		}
		log.debug("exclusive availability:"+exclusiveAvailability);
		return exclusiveAvailability;
	}

	/**
	 * Get the probability of a combination of stores being unavailable at the same time
	 * @param storageServiceCombination  a set of front end services
	 * @return unavailability of a combination of stores
	 */
	public static double getUnavailabilityOfCombination(Set<BackendService> storageServiceCombination) {
		double unavailabilityOfCombination;
		unavailabilityOfCombination = 0;
		for(BackendService storageService: storageServiceCombination){
			if (unavailabilityOfCombination == 0){
				unavailabilityOfCombination = 1-storageService.getAvailability();
			} else{
				unavailabilityOfCombination *= 1-storageService.getAvailability();
			}
			log.debug("unavailabilityOfCombination:"+unavailabilityOfCombination);
		}
		return unavailabilityOfCombination;
	}

	/**
	 * Get the availability of a combination of stores being available at the same time
	 * @param storageServiceCombination a set of front end services
	 * @return availability of a combination of stores
	 */
	public static double getAvailabilityOfCombination(Set<BackendService> storageServiceCombination) {
		double availabilityOfCombination;
		availabilityOfCombination = 0;
		for(BackendService storageService: storageServiceCombination){
			if (availabilityOfCombination == 0){
				availabilityOfCombination = storageService.getAvailability();
			} else{
				availabilityOfCombination *= storageService.getAvailability();
			}
		}
		return availabilityOfCombination;
	}

	/**
	 * Turn a list of data directory paths to a set of back end services with coinciding directory paths  
	 * @param storageDirectoryCombination list of data directory paths, each coinciding with the directory path of a back end service
	 * @return
	 */
	public Set<BackendService> getStorageServicesFromStorageDirectories(
			List<String> storageDirectoryCombination) {
		Set<BackendService> storages = new HashSet<BackendService>();
		for(String storageDirectory: storageDirectoryCombination){
			storages.add(getServiceByDataDirPath(storageDirectory));
		}
		return storages;
	}

	public void configure(String uniqueServiceName, Ini mountOptions) {
		get(uniqueServiceName).configure(uniqueServiceName, mountOptions);
		setChanged();
		notifyObservers();
	}

	/**
	 * Store the service name of each service in the service's data directory 
	 * @param fileName the name of the file to store the service name
	 */
	public void storeServiceNames(String fileName) {
		int index = 0; //index in case that several services store data in the same location
		for(BackendService service: services.values()){
			FileUtil.writeFile(new File(service.getDataDirPath()+"/"+fileName+"#"+index), service.getName());
			index++;
		}
	}
	
	/**
	 * Get a map from current service names to the names stored in the service's data directory 
	 * Assumes at most 100 services
	 * @param fileName the name of the file to read the service name from in each data directory
	 */
	public Map<String,String> getServiceNameMapping(String fileName) {
		Map<String,String> ret = new HashMap<String, String>();
		String storedServiceName;
		Set<Integer> usedIndizes = new HashSet<Integer>();
		for(BackendService service: services.values()){
			for(int index=0; index<100; index++){
				if(usedIndizes.contains(index)){
					continue;
				}
				storedServiceName = FileUtil.readFile(new File(service.getDataDirPath()+"/"+fileName+"#"+index));
				if(storedServiceName != null){
					usedIndizes.add(index);
					ret.put(service.getName(), storedServiceName);
					break;
				}
			}
		}
		return ret;
	}
}
