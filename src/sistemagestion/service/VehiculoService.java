/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.service;

import sistemagestion.dao.VehiculoDAO;
import sistemagestion.model.Vehiculo;

/**
 *
 * @author Maria Cristina
 */
public class VehiculoService {
    
    private VehiculoDAO vehiculoDAO;

    public VehiculoService() {
    }
    

    public VehiculoService(VehiculoDAO vehiculoDAo) {
        this.vehiculoDAO = vehiculoDAo;
    }
    //Valida si la placa ingresada ya existe 
    public boolean validarplaca(String placa){
        String resultado;
        resultado=vehiculoDAO.BuscarVehiculo(placa);
        if(resultado==null){
            return true;
        }
        
        
        return false;  
    }
    //Registrar lo vehiculos 
    public void registrarVehiculo(Vehiculo vehiculo){
        if(validarplaca(vehiculo.getPlaca())){
         vehiculoDAO.guardarVehiculo(vehiculo);
         
        }
         
    }
    
    
    
    
}
