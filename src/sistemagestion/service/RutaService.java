/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemagestion.service;

import sistemagestion.dao.RutaDAO;
import sistemagestion.model.Ruta;

/**
 *
 * @author Maria Cristina
 */
public class RutaService {
    
    private RutaDAO rutaDAO;

    public RutaService() {
    }
    

    public RutaService(RutaDAO rutaDAO) {
        this.rutaDAO = rutaDAO;
    }
    //ValidarRuta
    public boolean validarRuta(String codigo){
        String resultado;
        resultado=rutaDAO.buscarRuta(codigo);
        
        if(resultado==null){
            return true;
            
        }
        return false;
    }
    //RegistraRuta
    public void Registrarruta(Ruta ruta){
        if(validarRuta(ruta.getCodigo())){
            rutaDAO.guardarRuta(ruta);
        }
             
    } 
}
