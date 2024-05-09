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
public interface ILibraryDao<T> {
    Map<T, String> getList() throws Exception;

    void loadDataFromFile() throws Exception;

    void saveDataToFile() throws Exception;
}
