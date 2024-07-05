package spp.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 *
 * @author sapto
 */
public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder
                .addDecoratorPath("*/listbank*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/common/*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/potonganumkpop*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/kontrak/listrekananpopup", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/sppbantuan/listspdpopup/*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/bast/listpopupakun/*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/kontrak/listmetodepopup", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/kontrak/listkegiatanpopup", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/urusan/listfungsipopup", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/refakun/index/updateakun/*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/refakun/index/tambahakun/*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/spj/tambahkegiatan/*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/spj/editkegiatan/*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/spj/pilihkegiatan/*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/sp2dbtlls/listkbud*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/sp2dupgu/listkbud*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/npd/pilihkegiatanprogram*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/monitoringspd/listskpdkoorpopup*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/monitoringspd/listskpdblpopup*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/monitoringspd/listskpdbtlpopup*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/monitoringspd/detailspd*", "/WEB-INF/template/popup.jsp")
                .addDecoratorPath("*/print/*", "/WEB-INF/template/print.jsp")
                .addDecoratorPath("/*", "/WEB-INF/template/maintemplate.jsp")
                .addExcludedPath("/login")
                .addExcludedPath("/depan")
                .addExcludedPath("/deny")
                .addExcludedPath("/expired")
                .addExcludedPath("/json/*")
                .addExcludedPath("*/reports/*")
                .addExcludedPath("/xml/*");
    }
}
