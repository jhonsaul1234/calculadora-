package pe.edu.upeu.calcfx.servicio;

import pe.edu.upeu.calcfx.modelo.CalcTO;
<<<<<<< HEAD

import java.util.List;

public interface CalcServiceI {
    void guardarResultados(CalcTO to);
    List<CalcTO> obtenerResultados();
    void eliminarResultados(int index);
    void actualizarResultados(CalcTO to, int index);
}
=======
import java.util.List;

public interface CalcServiceI {

    public void guardarResultados(CalcTO to);//C
    public List<CalcTO> obtenerResultados();//R
    public void actualizarResultados(CalcTO to, int index);//U
    public void eliminarResultados(int index);//D


}
>>>>>>> 968eea5a0e24814f95c05c4ef5da9e3a700872a3
