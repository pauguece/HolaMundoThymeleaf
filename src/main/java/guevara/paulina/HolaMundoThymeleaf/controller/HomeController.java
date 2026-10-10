package guevara.paulina.HolaMundoThymeleaf.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(@RequestParam(name = "nombre", required = false, defaultValue = "Invitado") String nombre, Model model, HttpSession session) {
        model.addAttribute("mensajeBienvenida", "¡Hola mundo desde Spring boot!");
        model.addAttribute("nombreUsuario", nombre);
        session.setAttribute("operador", "Admin");
        return "index";
    }

    @GetMapping("/saludo")
    public String saludo(Model model) {
        LocalTime now = LocalTime.now();
        int hora = now.getHour();

        String saludo;
        String claseCss;

        if (hora >= 5 && hora < 12) {
            saludo = "¡Buenos días!";
            claseCss = "color: #198754";
        } else if (hora > 12 && hora < 19) {
            saludo = "Buenas tardes";
            claseCss = "color: #0d6efd";
        } else {
            saludo = "Buenas noches";
            claseCss = "color: #dc3545";
        }
        model.addAttribute("saludo", saludo);
        model.addAttribute("claseCss", claseCss);

        return "saludo";
    }

    @GetMapping("/catalogo")
    public String catalogo(Model model) {
        List<Map<String, Object>> juegos = new ArrayList<>();

        juegos.add(crearJuego("Animal crossing", "Life simulation", "Nintendo Switch", 900.0, true));
        juegos.add(crearJuego("Mario Kart", "Race", "Nintendo Switch", 1000.0, true));
        juegos.add(crearJuego("Untitled goose game", "Life simulation", "PC", 500.0, true));
        juegos.add(crearJuego("Elden Ring", "RPG", "Multiplataforma", 1200.0, true));
        juegos.add(crearJuego("Bloodborne", "Action RPG", "Play Station", 500.0, false));

        model.addAttribute("juegos", juegos);

        return "catalogo";
    }

    private Map<String, Object> crearJuego(String titulo, String genero, String plataforma, double precio, boolean disponible) {
        Map<String, Object> juego = new HashMap<>();

        juego.put("titulo", titulo);
        juego.put("genero", genero);
        juego.put("plataforma", plataforma);
        juego.put("precio", precio);
        juego.put("disponible", disponible);

        return juego;
    }
}