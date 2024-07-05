$(document).ready(function() {
    gridspdbantuan();

});

$("#kodekegiatanfilter").keyup(function() {
    gridspdbantuan();

});
$("#namakegiatanfilter").keyup(function() {
    gridspdbantuan();

});
$("#kodeakunfilter").keyup(function() {
    gridspdbantuan();

});
$("#namaakunfilter").keyup(function() {
    gridspdbantuan();
});
// -----
$("#kodekegiatanfilterbantuan").keyup(function() {
    gridspdbantuan();

});
$("#namakegiatanfilterbantuan").keyup(function() {
    gridspdbantuan();

});
$("#kodeakunfilterbantuan").keyup(function() {
    gridspdbantuan();

});
$("#namaakunfilterbantuan").keyup(function() {
    gridspdbantuan();
});

function gridspdbantuan() {

    var urljson = getbasepath() + "/monitoringspd/json/getlistspdbantuan";
    var urljsonbiaya = getbasepath() + "/monitoringspd/json/getlistspdbiaya";
    var urljsonbl = getbasepath() + "/monitoringspd/json/getlistspdbl";
    var urljsonbtl = getbasepath() + "/monitoringspd/json/getlistspdbtl";

    if (typeof myTableBtl == 'undefined') {
        myTableBtl = $('#spdbtl').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljsonbtl,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpdbtl", value: $('#idskpdbtl').val()},
                {name: "tahun", value: $('#tahun').val()}
                );
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
                //var namaKegiatan = "<input type='text' name='namaKegiatan' readonly='true'  style='border:none;margin:0;width:99%;'  kode='namaKegiatan' value='" + aData['kegiatan']['namaKegiatan'] + "' />";
                var idBtl = "<input type='hidden'  id='idBtl" + aData['idBtl'] + "'   name='idBtl" + aData['idBtl'] + "' value='" + aData['idBtl'] + "'/>  ";
                var idspd = "<input type='hidden'  id='idSpd" + aData['idSpd'] + "'   name='idSpd" + aData['idSpd'] + "' value='" + aData['idSpd'] + "'/>  ";
                var idskpdkoor = "<input type='hidden'  id='idskpdkoor" + aData['idBtl'] + "'   name='idskpdkoor" + aData['idBtl'] + "' value='" + aData['idskpdkoor'] + "'/>  ";
                var kooridskpd = $('#idskpdkoor').val();
                //var namaKegiatan = "<input type='text' name='namaKegiatan'  style='border:none;margin:0;width:99%;'  kode='namaKegiatan' value='" + aData['kegiatan']['namaKegiatan'] + "' />";
                // var edit = "<a href='" + getbasepath() + "/sppbantuan/edisppbantuan/" + aData['id'] + "/"+ aData['idBtlBantuan'] +"/"+ kooridskpd +"/" +aData['idSpd']+" ' class='icon-edit' ></a> - <a href='" + getbasepath() + "/sppbantuan/delsppbantuan/" + aData['id'] + "/"+ kooridskpd +"' class='icon-remove' ></a>";
                var nilaiSpd = accounting.formatNumber(aData['nilaiSpd']);
                var nilaiSpdCetak = accounting.formatNumber(aData['nilaiSpdCetak']);
                var nilaiSpdSah = accounting.formatNumber(aData['nilaiSpdSah']);

                if (nilaiSpd != 0) {
                    var urlspdbtl = "detailspd/spd/1/" + aData['skpd']['idSkpd'] + "/" + aData['akun']['idAkun'] + "/0/0";
                    var linkspdbtl = "<a href='" + urlspdbtl + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpd']) + "</a>";
                    nilaiSpd = linkspdbtl;
                }
                if (nilaiSpdCetak != 0) {
                    var urlspdbtl = "detailspd/cetak/1/" + aData['skpd']['idSkpd'] + "/" + aData['akun']['idAkun'] + "/0/0";
                    var linkspdcetakbtl = "<a href='" + urlspdbtl + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpdCetak']) + "</a>";
                    nilaiSpdCetak = linkspdcetakbtl;
                }
                if (nilaiSpdSah != 0) {
                    var urlspdbtl = "detailspd/sah/1/" + aData['skpd']['idSkpd'] + "/" + aData['akun']['idAkun'] + "/0/0";
                    var linkspdsahbtl = "<a href='" + urlspdbtl + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpdSah']) + "</a>";
                    nilaiSpdSah = linkspdsahbtl;
                }
                
                $('td:eq(0)', nRow).html(index);
                // $('td:eq(2)', nRow).html(getTanggal(aData['tglSpp']));
                //  $('td:eq(4)', nRow).html(namaKegiatan);
                $('td:eq(3)', nRow).html(accounting.formatNumber(aData['nilaiAnggaran']));
                $('td:eq(4)', nRow).html(nilaiSpd);
                $('td:eq(5)', nRow).html(nilaiSpdCetak);
                $('td:eq(6)', nRow).html(nilaiSpdSah);
                $('td:eq(7)', nRow).html(accounting.formatNumber(aData['nilaiSisaAnggaran']));
                //$('td:eq(6)', nRow).html(edit+idBtlBantuan);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "skpd.idSkpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "nilaiAnggaran", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "nilaiSpd", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSpdCetak", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSpdSah", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSisaAnggaran", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
            ]
        });

    }
    else
    {
        myTableBtl.fnClearTable(0);
        myTableBtl.fnDraw();
    }



    if (typeof myTable == 'undefined') {
        myTable = $('#spdbantuan').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpdkoor", value: $('#idskpdkoor').val()},
                {name: "tahun", value: $('#tahun').val()},
                // SPD BL FILTER
                {name: "namakegiatan", value: $('#namakegiatanfilter').val()},
                {name: "kodeakun", value: $('#kodeakunfilter').val()},
                {name: "kodekegiatan", value: $('#kodekegiatanfilter').val()},
                {name: "namaakun", value: $('#namaakunfilter').val()},
                //SPD BANTUAN FILTER
                {name: "namakegiatanbantuan", value: $('#namakegiatanfilterbantuan').val()},
                {name: "kodeakunbantuan", value: $('#kodeakunfilterbantuan').val()},
                {name: "kodekegiatanbantuan", value: $('#kodekegiatanfilterbantuan').val()},
                {name: "namaakunbantuan", value: $('#namaakunfilterbantuan').val()}

                );
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
                //var namaKegiatan = "<input type='text' name='namaKegiatan' readonly='true'  style='border:none;margin:0;width:99%;'  kode='namaKegiatan' value='" + aData['kegiatan']['namaKegiatan'] + "' />";
                var idBtlBantuan = "<input type='hidden'  id='idBtlBantuan" + aData['idBtlBantuan'] + "'   name='idBtlBantuan" + aData['idBtlBantuan'] + "' value='" + aData['idBtlBantuan'] + "'/>  ";
                var idspd = "<input type='hidden'  id='idSpd" + aData['idSpd'] + "'   name='idSpd" + aData['idSpd'] + "' value='" + aData['idSpd'] + "'/>  ";
                var idskpdkoor = "<input type='hidden'  id='idskpdkoor" + aData['idBtlBantuan'] + "'   name='idskpdkoor" + aData['idBtlBantuan'] + "' value='" + aData['idskpdkoor'] + "'/>  ";
                var kooridskpd = $('#idskpdkoor').val();
                var namaAkun = "<input type='text' name='namaAkun' size='25' style='border:none;margin:0;'  kode='namaAkun' value='" + aData['akun']['namaAkun'] + "' readOnly='true' />";
                var namaKegiatan = "<input type='text' name='namaKegiatan' size='25' style='border:none;margin:0;'  kode='namaKegiatan' value='" + aData['kegiatan']['namaKegiatan'] + "' readOnly='true' />";
                // var edit = "<a href='" + getbasepath() + "/sppbantuan/edisppbantuan/" + aData['id'] + "/"+ aData['idBtlBantuan'] +"/"+ kooridskpd +"/" +aData['idSpd']+" ' class='icon-edit' ></a> - <a href='" + getbasepath() + "/sppbantuan/delsppbantuan/" + aData['id'] + "/"+ kooridskpd +"' class='icon-remove' ></a>";

                var nilaiSpd = accounting.formatNumber(aData['nilaiSpd']);
                var nilaiSpdCetak = accounting.formatNumber(aData['nilaiSpdCetak']);
                var nilaiSpdSah = accounting.formatNumber(aData['nilaiSpdSah']);

                if (nilaiSpd != 0) {
                    var urlspdspdbantuan = "detailspd/spd/4/" + $('#idskpdkoor').val() + "/" + aData['akun']['idAkun'] + "/0/" + aData['idBtlBantuan'] + "";
                    var linknilaispdbantuan = "<a href='" + urlspdspdbantuan + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpd']) + "</a>";
                    nilaiSpd = linknilaispdbantuan;
                }
                if (nilaiSpdCetak != 0) {
                    var urlspdspdbantuan = "detailspd/cetak/4/" + $('#idskpdkoor').val() + "/" + aData['akun']['idAkun'] + "/0/" + aData['idBtlBantuan'] + "";
                    var linknilaispdbantuancetak = "<a href='" + urlspdspdbantuan + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpdCetak']) + "</a>";
                    nilaiSpdCetak = linknilaispdbantuancetak;
                }
                if (nilaiSpdSah != 0) {
                    var urlspdspdbantuan = "detailspd/sah/4/" + $('#idskpdkoor').val() + "/" + aData['akun']['idAkun'] + "/0/" + aData['idBtlBantuan'] + "";
                    var linknilaispdbantuansah = "<a href='" + urlspdspdbantuan + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpdSah']) + "</a>";
                    nilaiSpdSah = linknilaispdbantuansah;
                }

                $('td:eq(0)', nRow).html(index);
                // $('td:eq(2)', nRow).html(getTanggal(aData['tglSpp']));
                $('td:eq(2)', nRow).html(namaAkun);
                $('td:eq(4)', nRow).html(namaKegiatan);
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiAnggaran']));
                $('td:eq(6)', nRow).html(nilaiSpd);
                $('td:eq(7)', nRow).html(nilaiSpdCetak);
                $('td:eq(8)', nRow).html(nilaiSpdSah);
                $('td:eq(9)', nRow).html(accounting.formatNumber(aData['nilaiSisaAnggaran']));
                //$('td:eq(6)', nRow).html(edit+idBtlBantuan);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "skpd.idSkpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "nilaiAnggaran", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSpd", "bSortable": true, sDefaultContent: "-" , sClass: "kanan"},
                {"mDataProp": "nilaiSpdCetak", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSpdSah", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSisaAnggaran", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    if (typeof myTablebiaya == 'undefined') {
        myTablebiaya = $('#spdbiaya').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljsonbiaya,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()}
                );
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
                //var namaKegiatan = "<input type='text' name='namaKegiatan' readonly='true'  style='border:none;margin:0;width:99%;'  kode='namaKegiatan' value='" + aData['kegiatan']['namaKegiatan'] + "' />";
                var idBtlBantuan = "<input type='hidden'  id='idBtlBantuan" + aData['idBtlBantuan'] + "'   name='idBtlBantuan" + aData['idBtlBantuan'] + "' value='" + aData['idBtlBantuan'] + "'/>  ";
                var idspd = "<input type='hidden'  id='idSpd" + aData['idSpd'] + "'   name='idSpd" + aData['idSpd'] + "' value='" + aData['idSpd'] + "'/>  ";
                var idskpdkoor = "<input type='hidden'  id='idskpdkoor" + aData['idBtlBantuan'] + "'   name='idskpdkoor" + aData['idBtlBantuan'] + "' value='" + aData['idskpdkoor'] + "'/>  ";
                var kooridskpd = $('#idskpdkoor').val();

                var nilaiSpd = accounting.formatNumber(aData['nilaiSpd']);
                var nilaiSpdCetak = accounting.formatNumber(aData['nilaiSpdCetak']);
                var nilaiSpdSah = accounting.formatNumber(aData['nilaiSpdSah']);

                if (nilaiSpd != 0) {
                    var urlspdspdbiaya = "detailspd/spd/3/" + aData['skpd']['idSkpd'] + "/" + aData['akun']['idAkun'] + "/0/0";
                    var linknilaispdbiaya = "<a href='" + urlspdspdbiaya + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpd']) + "</a>";
                    nilaiSpd = linknilaispdbiaya;
                }
                if (nilaiSpdCetak != 0) {
                    var urlspdcetakbiaya = "detailspd/cetak/3/" + aData['skpd']['idSkpd'] + "/" + aData['akun']['idAkun'] + "/0/0";
                    var linknilaispdcetakbiaya = "<a href='" + urlspdcetakbiaya + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpdCetak']) + "</a>";
                    nilaiSpdCetak = linknilaispdcetakbiaya;
                }
                if (nilaiSpdSah != 0) {
                    var urlspdsahbiaya = "detailspd/sah/3/" + aData['skpd']['idSkpd'] + "/" + aData['akun']['idAkun'] + "/0/0";
                    var linknilaispdsahbiaya = "<a href='" + urlspdsahbiaya + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpdSah']) + "</a>";

                    nilaiSpdSah = linknilaispdsahbiaya;
                }
                // Bantuan idkegiatan = 0 :(( ,


                //var namaKegiatan = "<input type='text' name='namaKegiatan'  style='border:none;margin:0;width:99%;'  kode='namaKegiatan' value='" + aData['kegiatan']['namaKegiatan'] + "' />";
                // var edit = "<a href='" + getbasepath() + "/sppbantuan/edisppbantuan/" + aData['id'] + "/"+ aData['idBtlBantuan'] +"/"+ kooridskpd +"/" +aData['idSpd']+" ' class='icon-edit' ></a> - <a href='" + getbasepath() + "/sppbantuan/delsppbantuan/" + aData['id'] + "/"+ kooridskpd +"' class='icon-remove' ></a>";
                $('td:eq(0)', nRow).html(index);
                // $('td:eq(2)', nRow).html(getTanggal(aData['tglSpp']));
                //  $('td:eq(4)', nRow).html(namaKegiatan);
                $('td:eq(3)', nRow).html(accounting.formatNumber(aData['nilaiAnggaran']));
                $('td:eq(4)', nRow).html(nilaiSpd);
                $('td:eq(5)', nRow).html(nilaiSpdCetak);
                $('td:eq(6)', nRow).html(nilaiSpdSah);
                $('td:eq(7)', nRow).html(accounting.formatNumber(aData['nilaiSisaAnggaran']));
                //$('td:eq(6)', nRow).html(edit+idBtlBantuan);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "skpd.idSkpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "nilaiAnggaran", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"                                                                                                              },
                {"mDataProp": "nilaiSpd", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSpdCetak", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSpdSah", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSisaAnggaran", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
            ]
        });

    }
    else
    {
        myTablebiaya.fnClearTable(0);
        myTablebiaya.fnDraw();
    }

    if (typeof myTablebl == 'undefined') {
        myTablebl = $('#spdbl').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljsonbl,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpdbl", value: $('#idskpdbl').val()},
                {name: "tahun", value: $('#tahun').val()},
                {name: "namakegiatan", value: $('#namakegiatanfilter').val()},
                {name: "kodeakun", value: $('#kodeakunfilter').val()},
                {name: "kodekegiatan", value: $('#kodekegiatanfilter').val()},
                {name: "namaakun", value: $('#namaakunfilter').val()}
                );
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
                //var namaKegiatan = "<input type='text' name='namaKegiatan' readonly='true'  style='border:none;margin:0;width:99%;'  kode='namaKegiatan' value='" + aData['kegiatan']['namaKegiatan'] + "' />";
                var idBtlBantuan = "<input type='hidden'  id='idBtlBantuan" + aData['idBtlBantuan'] + "'   name='idBtlBantuan" + aData['idBtlBantuan'] + "' value='" + aData['idBtlBantuan'] + "'/>  ";
                var idspd = "<input type='hidden'  id='idSpd" + aData['idSpd'] + "'   name='idSpd" + aData['idSpd'] + "' value='" + aData['idSpd'] + "'/>  ";
                var idskpdkoor = "<input type='hidden'  id='idskpdkoor" + aData['idBtlBantuan'] + "'   name='idskpdkoor" + aData['idBtlBantuan'] + "' value='" + aData['idskpdkoor'] + "'/>  ";
                var kooridskpd = $('#idskpdkoor').val();
                var namaAkun = "<input type='text' name='namaAkun' size='25' style='border:none;margin:0;'  kode='namaAkun' value='" + aData['akun']['namaAkun'] + "' readOnly='true' />";
                var namaKegiatan = "<input type='text' name='namaKegiatan' size='25' style='border:none;margin:0;'  kode='namaKegiatan' value='" + aData['kegiatan']['namaKegiatan'] + "' readOnly='true' />";
                var nilaiSpd = accounting.formatNumber(aData['nilaiSpd']);
                var nilaiSpdCetak = accounting.formatNumber(aData['nilaiSpdCetak']);
                var nilaiSpdSah = accounting.formatNumber(aData['nilaiSpdSah']);

                 if (nilaiSpd != 0) {
                    var urlspdbl = "detailspd/spd/2/" + aData['skpd']['idSkpd'] + "/"+ aData['akun']['idAkun'] +"/"+ aData['kegiatan']['idKegiatan'] +"/0";
                    var linkspdbl = "<a href='" + urlspdbl + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpd']) + "</a>";
                    nilaiSpd = linkspdbl;
                }
                if (nilaiSpdCetak != 0) {
                    var urlspdbl = "detailspd/cetak/2/" + aData['skpd']['idSkpd'] + "/"+ aData['akun']['idAkun'] +"/"+ aData['kegiatan']['idKegiatan'] +"/0";
                    var linkspdcetakbl = "<a href='" + urlspdbl + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpdCetak']) + "</a>";
                    nilaiSpdCetak = linkspdcetakbl;
                }
                if (nilaiSpdSah != 0) {
                    var urlspdbl = "detailspd/sah/2/" + aData['skpd']['idSkpd'] + "/"+ aData['akun']['idAkun'] +"/"+ aData['kegiatan']['idKegiatan'] +"/0";
                    var linkspdsahbl = "<a href='" + urlspdbl + "' class='fancybox fancybox.iframe'  >" + accounting.formatNumber(aData['nilaiSpdSah']) + "</a>";
                    nilaiSpdSah = linkspdsahbl;
                }
                // var edit = "<a href='" + getbasepath() + "/sppbantuan/edisppbantuan/" + aData['id'] + "/"+ aData['idBtlBantuan'] +"/"+ kooridskpd +"/" +aData['idSpd']+" ' class='icon-edit' ></a> - <a href='" + getbasepath() + "/sppbantuan/delsppbantuan/" + aData['id'] + "/"+ kooridskpd +"' class='icon-remove' ></a>";
                $('td:eq(0)', nRow).html(index);
                // $('td:eq(2)', nRow).html(getTanggal(aData['tglSpp']));
                $('td:eq(2)', nRow).html(namaKegiatan);
                $('td:eq(4)', nRow).html(namaAkun);
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiAnggaran']));
                $('td:eq(6)', nRow).html(nilaiSpd);
                $('td:eq(7)', nRow).html(nilaiSpdCetak);
                $('td:eq(8)', nRow).html(nilaiSpdSah);
                $('td:eq(9)', nRow).html(accounting.formatNumber(aData['nilaiSisaAnggaran']));
                //$('td:eq(6)', nRow).html(edit+idBtlBantuan);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "skpd.idSkpd", "bSortable": false, sClass: "center"},
                {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "akun.kodeAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "akun.namaAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "nilaiAnggaran", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSpd", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSpdCetak", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSpdSah", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
                {"mDataProp": "nilaiSisaAnggaran", "bSortable": true, sDefaultContent: "-" ,sClass: "kanan"},
            ]
        });

    }
    else
    {
        myTablebl.fnClearTable(0);
        myTablebl.fnDraw();
    }

}

function pindahhalamanadd() {
    var idskpd = $.trim($("#idskpd").val());
    var idskpdkoor = $.trim($("#idskpdkoor").val());
    if (idskpdkoor) {
        window.location.replace(getbasepath() + "/spdbantuan/indexspdbantuan/" + idskpdkoor);
    } else {
        window.location.replace(getbasepath() + "/spdbantuan/indexspdbantuan");
    }

}

