package com.guvenlikKayit.TaluKayit.ui;

import com.guvenlikKayit.TaluKayit.backend.model.GirisCikisKayit;
import com.guvenlikKayit.TaluKayit.backend.service.KayitService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.stream.Collectors;

@Route("/admin")
@RolesAllowed("ADMIN")
public class AdminView extends VerticalLayout {
    private static final int PAGE_SIZE = 8;
    private List<GirisCikisKayit> allRecords;
    private GridCrud<GirisCikisKayit> crud; // Use GridCrud instead of a standalone Grid
    private int currentPage = 0;
    private int totalPages;

    public AdminView(KayitService service) {

        this.crud = new GridCrud<>(GirisCikisKayit.class, service);
        this.allRecords = service.findAll();
        crud.getGrid().setPageSize(PAGE_SIZE);
        totalPages = (int) Math.ceil((double) allRecords.size() / PAGE_SIZE);
        crud.getGrid().setColumns("aracPlaka", "cikisSaati", "donusSaati", "gidilenYer", "aracKullanicisi", "tarih");
        crud.getCrudFormFactory().setVisibleProperties("aracPlaka", "cikisSaati", "donusSaati", "gidilenYer", "aracKullanicisi");
        TextField searchField = new TextField();
        searchField.setWidth("300px");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.getElement().getStyle().set("border-radius", "20px")
                .set("padding", "10px")
                .set("box-shadow", "0 4px 8px rgba(0,0,0,0.2)");
        searchField.addValueChangeListener(e -> updateGrid(e.getValue().toLowerCase()));
        Button logoutButton = new Button("Logout", VaadinIcon.SIGN_OUT.create());
        logoutButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_SMALL);
        logoutButton.addClickListener(ee -> {
            VaadinSession.getCurrent().getSession().invalidate();
            UI.getCurrent().navigate("login");
        });
        HorizontalLayout logoutLayout = new HorizontalLayout(logoutButton);
        logoutLayout.setWidthFull();
        logoutLayout.setJustifyContentMode(JustifyContentMode.END);
        H1 header = new H1("TALU TEKSTIL GIRIS CIKIS KAYIT");
        header.getElement().getStyle().set("text-align", "center");
        HorizontalLayout paginationLayout = createPaginationLayout(searchField);
        VerticalLayout controlsLayout = new VerticalLayout(searchField, crud, logoutLayout, paginationLayout);
        controlsLayout.setAlignItems(Alignment.CENTER);
        controlsLayout.setWidthFull();
        add(
                header,
                controlsLayout
        );
        setAlignItems(Alignment.CENTER);
        setSizeFull();
        updateGrid("");
    }
    private void updateGrid(String searchTerm) {
        List<GirisCikisKayit> filteredRecords = allRecords.stream()
                .filter(record ->
                        record.getAracPlaka().toLowerCase().contains(searchTerm) ||
                                record.getGidilenYer().toLowerCase().contains(searchTerm) ||
                                record.getAracKullanicisi().toLowerCase().contains(searchTerm) ||
                                record.getCikisSaati().toLowerCase().contains(searchTerm)||
                                record.getDonusSaati().toLowerCase().contains(searchTerm)||
                                record.getTarih().toString().toLowerCase().contains(searchTerm)
                )
                .collect(Collectors.toList());
        int start = currentPage * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, filteredRecords.size());
        crud.getGrid().setItems(filteredRecords.subList(start, end));
        totalPages = (int) Math.ceil((double) filteredRecords.size() / PAGE_SIZE);
    }
    private HorizontalLayout createPaginationLayout(TextField searchField) {
        HorizontalLayout paginationLayout = new HorizontalLayout();
        paginationLayout.setAlignItems(Alignment.CENTER);
        Button previousButton = new Button(VaadinIcon.ANGLE_LEFT.create());
        previousButton.addClickListener(event -> {
            if (currentPage > 0) {
                currentPage--;
                updateGrid(searchField.getValue().toLowerCase());
            }
        });
        Button nextButton = new Button(VaadinIcon.ANGLE_RIGHT.create());
        nextButton.addClickListener(event -> {
            if (currentPage < totalPages - 1) {
                currentPage++;
                updateGrid(searchField.getValue().toLowerCase());
            }
        });
        paginationLayout.add(previousButton);
        int startPage = Math.max(currentPage - 2, 0);
        int endPage = Math.min(currentPage + 2, totalPages - 1);
        for (int i = startPage; i <= endPage; i++) {
            int pageNumber = i;
            Button pageButton = new Button(String.valueOf(i + 1));
            pageButton.addClickListener(event -> {
                currentPage = pageNumber;
                updateGrid(searchField.getValue().toLowerCase());
            });
            paginationLayout.add(pageButton);
        }
        paginationLayout.add(nextButton);
        return paginationLayout;
    }
}
