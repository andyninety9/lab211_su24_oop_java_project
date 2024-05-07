/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package BussinessLayer.Service;

import java.util.List;

/**
 *
 * @author duyma
 * @param <T>
 */
public interface IService<T> {
    void printList() throws Exception;

    List<T> getList() throws Exception;

    void add(T obj) throws Exception;

    void saveDataToFile() throws Exception;

}
