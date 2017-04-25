package com.ensat.controllers;

import com.ensat.entities.Agente;
import com.ensat.services.AgenteService;

import com.ensat.services.CentroService;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Agente controller.
 */
@Controller
public class AgenteController {

    private AgenteService agenteService;

    @Value("${spring.datasource.url}")
    private String urldb;

    @Value("${spring.datasource.username}")
    private String userdb;

    @Value("${spring.datasource.password}")
    private String passdb;

    @Autowired
    CentroService centrosService;

    @Autowired
    public void setAgenteService(AgenteService agenteService) {
        this.agenteService = agenteService;
    }

    /**
     * List all agentes.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/agentes", method = RequestMethod.GET)
    public String list(Model model, Pageable pageable) {
        //model.addAttribute("agentes", agenteService.listAllAgentes()); ***usar en el caso de prescindir del paginador**
        Page<Agente> agentePage = agenteService.findAll(pageable);
        PageWrapper<Agente> page = new PageWrapper<Agente>(agentePage, "/agentes");
        model.addAttribute("agentes", page.getContent());
        model.addAttribute("page", page);
        System.out.println("Returning agentes:");
        return "agente/agentes";
    }

    /**
     * View a specific agente by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("agente/{id}")
    public String showAgente(@PathVariable Integer id, Model model) {
        model.addAttribute("agente", agenteService.getAgenteById(id));
        return "agente/agenteshow";
    }

    // Afficher le formulaire de modification du Agente
    @RequestMapping("agente/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("agente", agenteService.getAgenteById(id));
        model.addAttribute("centros", centrosService.listAllCentros());
        return "agente/agenteform";
    }

    /**
     * New agente.
     *
     * @param model
     * @return
     */
    @RequestMapping("agente/new")
    public String newAgente(Model model) {
        model.addAttribute("agente", new Agente());
        model.addAttribute("centros", centrosService.listAllCentros());
        return "agente/agenteform";
    }

    /**
     * Save agente to database.
     *
     * @param agente
     * @return
     */
    @RequestMapping(value = "agente", method = RequestMethod.POST)
    public String saveAgente(Agente agente) {
        System.out.println(agente);
        agenteService.saveAgente(agente);
        return "redirect:/agente/" + agente.getId();
    }

    /**
     * Delete agente by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("agente/delete/{id}")
    public String delete(@PathVariable Integer id) {
        agenteService.deleteAgente(id);
        return "redirect:/agentes";
    }

    @RequestMapping(value = "/pdf", method = RequestMethod.GET)
    public @ResponseBody
    void pdf(HttpServletResponse response) throws SQLException {

        try {
            /*  Par el caso que tengas que compilar el jrxml, como ya está compilado nada más es cargarlo *****
           JasperDesign design = JRXmlLoader.load("src/main/resources/reports/obra/obraReport.jrxml");
           JasperReport report = JasperCompileManager.compileReport(design);
             */
            JasperReport report;
            report = (JasperReport) JRLoader.loadObjectFromFile("src/main/resources/reports/agenteReport.jasper");

            //Map<String, Object> parameterMap = new HashMap<>();
            //Iterable<Obra> agentes = obraRepository.findAll();
            //JRDataSource jPDatasource = new JRBeanCollectionDataSource((Collection<Obra>) agentes);
            //parameterMap.put("datasource", agentes);
            Connection conn = DriverManager.getConnection(urldb, userdb, passdb);
            Date date = new Date();
            DateFormat hourdateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
            String fechaActual = hourdateFormat.format(date);

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, new HashMap<>(), conn);
            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "inline; filename=Imprimir-Agentes-" + fechaActual + ".pdf");

            final OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        } catch (JRException e) {
            System.out.println(e);
        } catch (IOException ex) {
            Logger.getLogger(com.ensat.controllers.AgenteController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
