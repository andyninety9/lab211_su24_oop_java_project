/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package DataLayer;

import DataLayer.Event.IEventDao;

/**
 *
 * @author duyma
 */
public interface IDaoFactory {
    IEventDao eventDao() throws Exception;
}
