/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package DataLayer;

import java.util.List;

/**
 *
 * @author duyma
 * @param <T>
 */
public interface IFileManager<T> {
    List<T> readDataFromFile() throws Exception;

    void writeDataToFile(List<T> list) throws Exception;

}
