/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package BussinessLayer.Service;

import java.util.Map;

/**
 *
 * @author duyma
 * @param <T>
 */
public interface IService<T> {
    void add(T obj) throws Exception;

    void delete(String id) throws Exception;

    void update(String id) throws Exception;

    Map<String, T> getList() throws Exception;

    void saveDataToFile() throws Exception;
}
