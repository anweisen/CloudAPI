package net.anweisen.cloudapi.node.module;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 *
 * @see ModuleController#getState()
 */
public enum ModuleState {

	/**
	 * @see ModuleController#loadModule()
	 */
	LOADED,

	/**
	 * @see ModuleController#enableModule()
	 */
	ENABLED,

	/**
	 * @see ModuleController#disableModule()
	 */
	DISABLED

}
