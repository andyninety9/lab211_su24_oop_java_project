/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package DataLayer.Event;

import BussinessLayer.Entity.Event;
import java.util.List;

/**
 *
 * @author duyma
 * @param <T>
 */
public interface IEventDao<T> {
    void loadDataFromFile() throws Exception;

    void saveDataToFile() throws Exception;

    void addNew(Event event) throws Exception;

    List<Event> getList() throws Exception;
}
