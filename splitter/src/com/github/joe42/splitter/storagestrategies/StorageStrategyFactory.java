package com.github.joe42.splitter.storagestrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.github.joe42.splitter.backend.BackendService;
import com.github.joe42.splitter.backend.BackendServices;

/**
 * Responsible for creating, configuring and pooling StorageStrategies.
 */
public class StorageStrategyFactory {
	public static enum AvailableStorageStrategies {Roundrobin};
	private RoundRobinStorageStrategy roundRobin = null;
	private UseAllInParallelStorageStrategy useInParallel = null;
	private BackendServices services;
	private boolean changeToCurrentStrategy = true;
	private StorageStrategy previousStorageStrategy = null;
	private Set<String> dataDirPaths = new TreeSet<String>();
	public StorageStrategyFactory(BackendServices services){
		this.services = services;
	}

	/**
	 * Get a strategy, possibly creating and (re)configuring it.
	 * @param strategyName
	 * @param redundancy
	 * @return
	 */
	public StorageStrategy createStrategy(String strategyName, int redundancy) {
		StorageStrategy ret = null;
		if(strategyName.equals("UseAllInParallel")){
			if(useInParallel == null){
				dataDirPaths = new TreeSet<String>(services.getDataDirPaths());
				useInParallel = new UseAllInParallelStorageStrategy(services);
			}
			if( !useInParallel.getStorageServices().equals(services) ) {
				useInParallel.setStorageServices(services);
			}
			useInParallel.setRedundancy(redundancy);
			changeToCurrentStrategy = useInParallel.changeToCurrentStrategy(previousStorageStrategy);
			previousStorageStrategy = useInParallel;
			ret = useInParallel;
		} else { //RoundRobin as default
			if(roundRobin == null || ! dataDirPaths.equals(new TreeSet<String>(services.getDataDirPaths()))){
				dataDirPaths = new TreeSet<String>(services.getDataDirPaths());
				roundRobin = new RoundRobinStorageStrategy(services);
			}
			if( !roundRobin.getStorageServices().equals(services) ) {
				roundRobin.setStorageServices(services);
			}
			roundRobin.setRedundancy(redundancy);
			changeToCurrentStrategy = roundRobin.changeToCurrentStrategy(previousStorageStrategy);
			previousStorageStrategy = roundRobin;
			ret = roundRobin;
		}
		return ret;
	}
	
	/**
	 * @return true iff the the splitter should change his already stored files according to this storage strategy's attributes 
	 */
	public boolean changeToCurrentStrategy(){
		return changeToCurrentStrategy ;
	}
}
