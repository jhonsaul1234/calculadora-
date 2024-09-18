package pe.edu.upeu.calcfx.servicio;

import pe.edu.upeu.calcfx.modelo.CalcTO;

import java.util.List;

public interface CalcServiceI {
    void guardarResultados(CalcTO to);
    List<CalcTO> obtenerResultados();
    void eliminarResultados(int index);
    void actualizarResultados(CalcTO to, int index);
}