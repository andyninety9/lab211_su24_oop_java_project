/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package DataLayer;

/**
 *
 * @author duyma
 */
public interface IDaoFactory {
    ILibraryDao bookDao() throws Exception;

    ILibraryDao userDao() throws Exception;

    ILibraryDao borrowDao() throws Exception;

}