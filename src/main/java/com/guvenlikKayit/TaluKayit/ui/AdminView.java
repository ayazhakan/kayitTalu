package com.guvenlikKayit.TaluKayit.ui;

import com.guvenlikKayit.TaluKayit.backend.model.GirisCikisKayit;
import com.guvenlikKayit.TaluKayit.backend.service.KayitService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@Route("admin")
@RolesAllowed("ADMIN")
public class AdminView extends VerticalLayout {

    public AdminView(KayitService service){

        var crud = new GridCrud<>(GirisCikisKayit.class, service);
        crud.getGrid().setColumns("aracPlaka", "cikisSaati", "donusSaati", "gidilenYer", "aracKullanicisi", "tarih");
        crud.getCrudFormFactory().setVisibleProperties("aracPlaka", "cikisSaati", "donusSaati", "gidilenYer", "aracKullanicisi");

        // Fetch all records initially
        List<GirisCikisKayit> allRecords = service.findAll();

        // Create the search field
        TextField searchField = new TextField();
        searchField.setWidth("50%");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);

        // Add a listener for when the search field changes
        searchField.addValueChangeListener(e -> {
            String searchTerm = e.getValue().toLowerCase();
            // Filter the records based on the search term
            List<GirisCikisKayit> filteredRecords = allRecords.stream()
                    .filter(record ->
                            record.getAracPlaka().toLowerCase().contains(searchTerm) ||
                                    record.getGidilenYer().toLowerCase().contains(searchTerm) ||
                                    record.getAracKullanicisi().toLowerCase().contains(searchTerm) ||
                                    record.getTarih().toString().toLowerCase().contains(searchTerm)
                    )
                    .collect(Collectors.toList());
            // Update the grid with filtered records
            crud.getGrid().setItems(filteredRecords);
        });

        // Add components to the layout
        add(
                new H1("TALU TEKSTIL GIRIS CIKIS KAYIT"),
                searchField,
                crud
        );
    }
}
