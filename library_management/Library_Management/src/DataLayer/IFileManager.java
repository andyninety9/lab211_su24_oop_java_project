/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package DataLayer;

import java.util.Map;

/**
 *
 * @author duyma
 * @param <T>
 */
public interface IFileManager<T> {
    <K, V> Map<K, V> readDataFromFile() throws Exception;

    <K, V> void writeDataToFile(Map<T, String> list) throws Exception;

}
