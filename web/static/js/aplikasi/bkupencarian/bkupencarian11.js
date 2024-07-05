$(document).ready(function() {
    setPanel(1);

    $("#nipfilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridpptk();
        }
    });

});

// global variable
var tglgabawal, tglgabakhir;

function cleardokumen() {
    $("#noDokumen").val("");
    gridbkunodok();
}

function cekjenisgrid(){
    var jenis = $("#jenis").val();
    if (jenis == 1) {  // No Dokumen
        gridbkunodok();

    } else if (jenis == 2) {  // Sisa Uang di PPTK
        gridpptk();
        
    } else if (jenis == 3) {  // Kegiatan
        gridbkukegiatan();
        getNilaiAnggaran();

    } else if (jenis == 4) {  // Cek Setoran Tunai
        gridbku();

    } else if (jenis == 5) {  // Setoran BUD
        gridbku();

    } else if (jenis == 6) {  // Pajak
         setPanel(jenis);
        
    } else if (jenis == 7) {  // Sisa Uang per PPTK
        gridbkupptk2();
    }
    
}

function setPanel(jenis) {
    
    document.getElementById("labelnodok").style.display = "none";
    document.getElementById("labelkegiatan").style.display = "none";
    document.getElementById("labeljenisdok").style.display = "none";
    document.getElementById("labeljenispajak").style.display = "none";
    document.getElementById("labelpagu").style.display = "none";
    document.getElementById("labelpptk2").style.display = "none";

    $("#idKegiatan").val(99999);
    $("#keteranganKeg").val("");
    $("#noDokumen").val("");
    $("#nilaianggaran").val("");
    $("#nipPPTK").val("");
    $("#namaPPTK").val("");
    $("#keteranganPPTK").val("");

    $('#pptkgrid').hide();
    $('#standardgrid').hide();
    $('#kegiatangrid').hide();
    $('#pptk2grid').hide();
    $('#nodokgrid').hide();
    $('#allpajakgrid').hide();

    // Set Grid
    if (jenis == 1) { // No Dokumen
        $('#nodokgrid').show();
        
    } else if (jenis == 2) { // Sisa Uang di PPTK
        $('#pptkgrid').show();

    } else if (jenis == 3) { // Kegiatan
        $('#kegiatangrid').show();

    } else if (jenis == 4 || jenis == 5 ) { // Cek Setor Tunai, Setoran BUD
        $('#standardgrid').show();

    } else if (jenis == 6) { // Pajak
        if ($("#jenispajak").val() == "PS") {
            $('#allpajakgrid').show();
        } else {
            $('#standardgrid').show();
        }

    } else if (jenis == 7) { // Sisa Uang per PPTK
        $('#pptk2grid').show();

    }

    // Set panel
    if (jenis == 1) {  // No Dokumen
        document.getElementById("labelnodok").style.display = "block";
        document.getElementById("labeljenisdok").style.display = "block";
        gridbkunodok();

    } else if (jenis == 2) {  // Sisa Uang di PPTK
        gridpptk();

    } else if (jenis == 3) {  // Kegiatan
        document.getElementById("labelkegiatan").style.display = "block";
        document.getElementById("labelpagu").style.display = "block";
        gridbkukegiatan();

    } else if (jenis == 4) {  // Cek Setoran Tunai
        gridbku();

    } else if (jenis == 5) {  // Setoran BUD
        gridbku();

    } else if (jenis == 6) {  // Pajak
        document.getElementById("labeljenispajak").style.display = "block";
        if ($("#jenispajak").val() == "PS") {
            gridallpajak();
        } else {
            gridbku();
        }
        
    } else if (jenis == 7) {  // Pajak
        document.getElementById("labelpptk2").style.display = "block";
        gridbkupptk2();
    }
    
}

function gridbku() {

    if (typeof tablebku == 'undefined') {

        tablebku = $('#jourtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkupencarian/json/listpencarian",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $("#idskpdpop").val()},
                {name: "tahun", value: $("#tahun").val()},
                {name: "jenis", value: $("#jenis").val()},
                {name: "idkegiatan", value: $("#idKegiatan").val()},
                {name: "jenispajak", value: $("#jenispajak").val()},
                {name: "jenisdokumen", value: $("#jenisdok").val()},
                {name: "nodokumen", value: $("#noDokumen").val()},
                {name: "namafilter", value: ""},
                {name: "nipfilter", value: ""},
                {name: "tglAwal", value: tglgabawal}, //$("#tglAwal").val()
                {name: "tglAkhir", value: tglgabakhir} //$("#tglAkhir").val()
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + aData['nilaiMasuk'] + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true'/>";
                var nilaikas = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['saldoKas'] + "' readOnly='true'/>";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(nilaimasuk);
                $('td:eq(6)', nRow).html(nilaikeluar);
                $('td:eq(7)', nRow).html(nilaikas);
                //noBukti, noBKU, inboxFile, nilaiMasuk, nilaiKeluar, saldoKas
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noBukti", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "tglPosting", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center"},
                {"mDataProp": "inboxFile", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "left"},
                {"mDataProp": "saldoKas", "bSortable": false, sClass: "left"}
            ]
        });
    }
    else
    {
        tablebku.fnClearTable(0);
        tablebku.fnDraw();
    }
}

function gridpptk() {

    if (typeof tablepptk == 'undefined') {

        tablepptk = $('#pptktable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkupencarian/json/listpencarian",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $("#idskpdpop").val()},
                {name: "tahun", value: $("#tahun").val()},
                {name: "jenis", value: $("#jenis").val()},
                {name: "idkegiatan", value: $("#idKegiatan").val()},
                {name: "jenispajak", value: $("#jenispajak").val()},
                {name: "jenisdokumen", value: $("#jenisdok").val()},
                {name: "nodokumen", value: $("#noDokumen").val()},
                {name: "namafilter", value: $("#namafilter").val()},
                {name: "nipfilter", value: $("#nipfilter").val()},
                {name: "tglAwal", value: tglgabawal}, //$("#tglAwal").val()
                {name: "tglAkhir", value: tglgabakhir} //$("#tglAkhir").val()
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + aData['nilaiMasuk'] + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true'/>";
                var nilaikas = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['saldoKas'] + "' readOnly='true'/>";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(7)', nRow).html(nilaimasuk);
                $('td:eq(8)', nRow).html(nilaikeluar);
                $('td:eq(9)', nRow).html(nilaikas);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noBukti", "bSortable": false, sClass: "center", "sWidth": "5%"},
                {"mDataProp": "nipPptk", "bSortable": false, sClass: "left", "sWidth": "15%"},
                {"mDataProp": "namaPptk", "bSortable": false, sClass: "left", "sWidth": "15%"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "left", "sWidth": "5%"},
                {"mDataProp": "tglPosting", "bSortable": false, sClass: "center", "sWidth": "5%"},
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center", "sWidth": "5%"},
                {"mDataProp": "inboxFile", "bSortable": false, sClass: "left", "sWidth": "5%"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "left", "sWidth": "15%"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "left", "sWidth": "15%"},
                {"mDataProp": "saldoKas", "bSortable": false, sClass: "left", "sWidth": "15%"}
            ]
        });
    }
    else
    {
        tablepptk.fnClearTable(0);
        tablepptk.fnDraw();
    }
}

function gridbkunodok() {

    if (typeof tablebkunodok == 'undefined') {

        tablebkunodok = $('#nodoktable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkupencarian/json/listpencarian",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $("#idskpdpop").val()},
                {name: "tahun", value: $("#tahun").val()},
                {name: "jenis", value: $("#jenis").val()},
                {name: "idkegiatan", value: $("#idKegiatan").val()},
                {name: "jenispajak", value: $("#jenispajak").val()},
                {name: "jenisdokumen", value: $("#jenisdok").val()},
                {name: "nodokumen", value: $("#noDokumen").val()},
                {name: "namafilter", value: ""},
                {name: "nipfilter", value: ""},
                {name: "tglAwal", value: tglgabawal}, //$("#tglAwal").val()
                {name: "tglAkhir", value: tglgabakhir} //$("#tglAkhir").val()
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + aData['nilaiMasuk'] + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true'/>";
                var nilaikas = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['saldoKas'] + "' readOnly='true'/>";
                var kodeakun = "<textarea id='kodeakun" + index + "'readonly style='border:none;margin:0;width:350px;'>" + aData['kodeakun'] + "</textarea>";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(kodeakun);
                $('td:eq(5)', nRow).html(nilaimasuk);
                $('td:eq(6)', nRow).html(nilaikeluar);
                $('td:eq(7)', nRow).html(nilaikas);
                //noBukti, noBKU, inboxFile, nilaiMasuk, nilaiKeluar, saldoKas
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noBukti", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "left"},
                {"mDataProp": "tglPosting", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center", },
                {"mDataProp": "inboxFile", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "left"},
                {"mDataProp": "saldoKas", "bSortable": false, sClass: "left"}
            ]
        });
    }
    else
    {
        tablebkunodok.fnClearTable(0);
        tablebkunodok.fnDraw();
    }
}

function gridbkukegiatan() {

    if (typeof tablebkukeg == 'undefined') {

        tablebkukeg = $('#kegiatantable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkupencarian/json/listpencarian",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $("#idskpdpop").val()},
                {name: "tahun", value: $("#tahun").val()},
                {name: "jenis", value: $("#jenis").val()},
                {name: "idkegiatan", value: $("#idKegiatan").val()},
                {name: "jenispajak", value: $("#jenispajak").val()},
                {name: "jenisdokumen", value: $("#jenisdok").val()},
                {name: "nodokumen", value: $("#noDokumen").val()},
                {name: "namafilter", value: ""},
                {name: "nipfilter", value: ""},
                {name: "tglAwal", value: tglgabawal}, //$("#tglAwal").val()
                {name: "tglAkhir", value: tglgabakhir} //$("#tglAkhir").val()
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + aData['nilaiMasuk'] + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true'/>";
                var nilaikas = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['saldoKas'] + "' readOnly='true'/>";
                var kodeakun = "<textarea id='kodeakun" + index + "'readonly style='border:none;margin:0;width:350px;'>" + aData['kodeakun'] + "</textarea>";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(kodeakun);
                $('td:eq(6)', nRow).html(nilaimasuk);
                $('td:eq(7)', nRow).html(nilaikeluar);
                $('td:eq(8)', nRow).html(nilaikas);
                //noBukti, noBKU, inboxFile, nilaiMasuk, nilaiKeluar, saldoKas
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noBukti", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "left"},
                {"mDataProp": "tglPosting", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center"},
                {"mDataProp": "inboxFile", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "left"},
                {"mDataProp": "saldoKas", "bSortable": false, sClass: "left"}
                
            ]
        });
    }
    else
    {
        tablebkukeg.fnClearTable(0);
        tablebkukeg.fnDraw();
    }
}

function gridbkupptk2() {

    if (typeof tablebkupptk2 == 'undefined') {

        tablebkupptk2 = $('#pptk2table').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkupencarian/json/listpencarian",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $("#idskpdpop").val()},
                {name: "tahun", value: $("#tahun").val()},
                {name: "jenis", value: $("#jenis").val()},
                {name: "idkegiatan", value: $("#idKegiatan").val()},
                {name: "jenispajak", value: $("#jenispajak").val()},
                {name: "jenisdokumen", value: $("#jenisdok").val()},
                {name: "nodokumen", value: $("#noDokumen").val()},
                {name: "namafilter", value: $("#namaPPTK").val()},
                {name: "nipfilter", value: $("#nipPPTK").val()},
                {name: "tglAwal", value: tglgabawal}, //$("#tglAwal").val()
                {name: "tglAkhir", value: tglgabakhir} //$("#tglAkhir").val()
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + aData['nilaiMasuk'] + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true'/>";
                var nilaikas = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['saldoKas'] + "' readOnly='true'/>";
                //var kodeakun = "<textarea id='kodeakun" + index + "'readonly style='border:none;margin:0;width:350px;'>" + aData['kodeakun'] + "</textarea>";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(5)', nRow).html(nilaimasuk);
                $('td:eq(6)', nRow).html(nilaikeluar);
                $('td:eq(7)', nRow).html(nilaikas);

                return nRow;
            },
            "aoColumns": [
                //, "sWidth": "5%"
                {"mDataProp": "noBukti", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "tglPosting", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center"},
                {"mDataProp": "inboxFile", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "left"},
                {"mDataProp": "saldoKas", "bSortable": false, sClass: "left"}
            ]
        });
    }
    else
    {
        tablebkupptk2.fnClearTable(0);
        tablebkupptk2.fnDraw();
    }
}

function gridallpajak() {

    if (typeof tablebkuallpajak == 'undefined') {

        tablebkuallpajak = $('#allpajaktable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/bkupencarian/json/listpencarian",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $("#idskpdpop").val()},
                {name: "tahun", value: $("#tahun").val()},
                {name: "jenis", value: $("#jenis").val()},
                {name: "idkegiatan", value: $("#idKegiatan").val()},
                {name: "jenispajak", value: $("#jenispajak").val()},
                {name: "jenisdokumen", value: $("#jenisdok").val()},
                {name: "nodokumen", value: $("#noDokumen").val()},
                {name: "namafilter", value: ""},
                {name: "nipfilter", value: ""},
                {name: "tglAwal", value: tglgabawal}, //$("#tglAwal").val()
                {name: "tglAkhir", value: tglgabakhir} //$("#tglAkhir").val()
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

            },
            "fnServerData": function(sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + aData['nilaiMasuk'] + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['nilaiKeluar'] + "' readOnly='true'/>";
                var nilaikas = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + aData['saldoKas'] + "' readOnly='true'/>";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(6)', nRow).html(nilaimasuk);
                $('td:eq(7)', nRow).html(nilaikeluar);
                $('td:eq(8)', nRow).html(nilaikas);
                //noBukti, noBKU, inboxFile, nilaiMasuk, nilaiKeluar, saldoKas
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "noBukti", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "kodeTransaksi", "bSortable": false, sClass: "left"},
                {"mDataProp": "tglPosting", "bSortable": false, sClass: "center"},
                {"mDataProp": "noBKU", "bSortable": false, sClass: "center"},
                {"mDataProp": "inboxFile", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "left"},
                {"mDataProp": "saldoKas", "bSortable": false, sClass: "left"}
            ]
        });
    }
    else
    {
        tablebkuallpajak.fnClearTable(0);
        tablebkuallpajak.fnDraw();
    }
}

function cari() {
    
    $("#namafilter").keyup(function () {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridpptk();
        }
    });
}

function getNilaiAnggaran() {
    
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpdpop').val();
    var idkegiatan = $('#idKegiatan').val();

    $.getJSON(getbasepath() + "/bkupencarian/json/getNilaiAnggaran", {tahun: tahun, idskpd: idskpd, idkegiatan: idkegiatan},
    function(result) {
        var banyak;
        console.log("nilai anggaran = "+result.aData['nilaiAnggaran']);
        $("#nilaianggaran").val(accounting.formatNumber(result.aData['nilaiAnggaran'], 2, '.', ","));
       
    });

}

function getTanggalAwalAkhir() {
    var tglawal = $("#tglAwal").val();
    var tglakhir = $("#tglAkhir").val();
    var d,m,y,dd, mm, yy; //tglgabawal, tglgabakhir

    dd = tglawal.substr(0, 2);
    mm = tglawal.substr(3, 2);
    yy = tglawal.substring(6);
    tglgabawal = yy + mm + dd;
    
    d = tglakhir.substr(0, 2);
    m = tglakhir.substr(3, 2);
    y = tglakhir.substring(6);
    tglgabakhir = y + m + d;
    
    //console.log("tgl awal == "+tglgabawal);
    //console.log("tgl akhir == "+tglgabakhir);
}
