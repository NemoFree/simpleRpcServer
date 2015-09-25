package com.rpc.client;

/**
 * @author lizd
 *	�첽���÷��ش���
 * @param <T>
 */
public interface Callback<T> {


	
  void handleResult(T result);
  


  
  void handleError(Throwable error);
}