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
            System.out.println("Ruta registrada correctamente");
        }else{
             System.out.println("La ruta ya existe");
        }
             
    }
    
    
    public String buscarRuta(String codigo){

    String resultado = rutaDAO.buscarRuta(codigo);

    if(resultado == null){
        System.out.println("La ruta no existe");
    }else{
        System.out.println("Ruta encontrada: " + resultado);
    }

    return resultado;
}
    //actualizarRuta
    
     public void actualizarRuta(Ruta ruta){

        if(!validarRuta(ruta.getCodigo())){
            rutaDAO.actualizarRuta(ruta);
            System.out.println("Ruta modificada");
        }else{
            System.out.println("La ruta no existe");
        }

    }
     //eliminarRuta
       public void eliminarRuta(String codigo){

        if(!validarRuta(codigo)){
            rutaDAO.eliminarRuta(codigo);
            System.out.println("Ruta eliminada");
        }else{
            System.out.println("La ruta no existe");
        }

    }
    
    
    
}
