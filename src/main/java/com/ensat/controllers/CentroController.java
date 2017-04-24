package com.ensat.controllers;

import com.ensat.entities.Centro;
import com.ensat.services.CentroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Centro controller.
 */
@Controller
public class CentroController {

    private CentroService centroService;

    @Autowired
    public void setCentroService(CentroService centroService) {
        this.centroService = centroService;
    }

    /**
     * List all centros.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/centros", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("centros", centroService.listAllCentros());
        System.out.println("Returning centros:");
        return "centro/centros";
    }

    /**
     * View a specific centro by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("centro/{id}")
    public String showCentro(@PathVariable Integer id, Model model) {
        model.addAttribute("centro", centroService.getCentroById(id));
        return "centro/centroshow";
    }

    // Afficher le formulaire de modification du Centro
    @RequestMapping("centro/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("centro", centroService.getCentroById(id));
        return "centro/centroform";
    }

    /**
     * New centro.
     *
     * @param model
     * @return
     */
    @RequestMapping("centro/new")
    public String newCentro(Model model) {
        model.addAttribute("centro", new Centro());
        return "centro/centroform";
    }

    /**
     * Save centro to database.
     *
     * @param centro
     * @return
     */
    @RequestMapping(value = "centro", method = RequestMethod.POST)
    public String saveCentro(Centro centro) {
        centroService.saveCentro(centro);
        return "redirect:/centro/" + centro.getId();
    }

    /**
     * Delete centro by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("centro/delete/{id}")
    public String delete(@PathVariable Integer id) {
        centroService.deleteCentro(id);
        return "redirect:/centros";
    }

}

