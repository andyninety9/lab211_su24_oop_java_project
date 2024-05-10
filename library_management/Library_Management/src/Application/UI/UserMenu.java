/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Application.UI;

import BussinessLayer.Entity.User;
import BussinessLayer.Service.IService;

/**
 *
 * @author duyma
 */
public class UserMenu {
    IService<User> service;

    public UserMenu() {
    }

    public UserMenu(IService<User> service) {
	this.service = service;
    }

    public void processMenuUser() {

    }
}
