/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package DataLayer;

import BussinessLayer.Entity.ILibraryObject;
import java.util.List;
import java.util.Map;

/**
 *
 * @author duyma
 * @param <T>
 */
public interface IFileManager<T> {
    <T extends ILibraryObject> Map<T, String> readDataFromFile() throws Exception;

    <T extends ILibraryObject> void writeDataToFile(Map<T, String> list) throws Exception;

}
