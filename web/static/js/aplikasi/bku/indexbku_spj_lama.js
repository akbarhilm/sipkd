$(document).ready(function() {
    getWilayah();
    setBulan();
    getTotal();
    gridbku();
    //getTotal();

    if ($("#idskpd").val() == "761") {
        document.getElementById("labelwilayah").style.display = "block";
    } else {
        document.getElementById("labelwilayah").style.display = "none";
    }

});

function setBulan() {
    var currentbulan;

    $("#tglHide").datepicker("setDate", new Date());
    currentbulan = $("#tglHide").val();
    var mm = currentbulan.substr(3, 2);
    $('#bulan').val(mm);
}

function gridbku() {

    if (typeof myTable == 'undefined') {
        myTable = $('#jourtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "aLengthMenu": [[25, 50, 100, 200, 5000], [25, 50, 100, 200, "All"]],
            "iDisplayLength": 50,
            "sAjaxSource": getbasepath() + "/bku/json/listbkuindex",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                // $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});

            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $("#tahun").val()},
                {name: "idskpd", value: $("#idskpd").val()},
                {name: "bulan", value: $("#bulan").val()}
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
                var dpost = aData['tglPosting'];
                var tglPost = "";
                var tahun, mm, dd;
                if (index > 1) {
                    tahun = dpost.substr(0, 4);
                    mm = dpost.substr(4, 2);
                    dd = dpost.substr(6, 2);
                    tglPost = dd + "/" + mm + "/" + tahun;
                    //console.log("tanggal posting = "+tglPost);
                }

                var nilaisaldo = accounting.formatNumber(aData['saldoKas'], 2, '.', ",");
                var nilaim = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                var nilaik = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + nilaim + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + nilaik + "' readOnly='true'/>";
                var saldokas = "<input type='text' name='saldokas" + index + "' id='saldokas" + index + "'  class='inputmoney' value='" + nilaisaldo + "' readOnly='true' />";
                var idtrans = "<input type='hidden' id='kodetransaksi" + index + "' value='" + aData['idTransaksi'] + "' />";
                var uraian = "<textarea id='uraianbukti" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['uraianBukti'] + "</textarea>";
                var kegiatan = "<textarea id='kegiatan" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['ketKegiatan'] + "</textarea>";
                var edit = "<a href='" + getbasepath() + "/bku/editbku/" + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editpajak = "<a href='" + getbasepath() + "/bku/editbkupajak/" + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editStUp = "<a href='" + getbasepath() + "/bku/editbkustup/" + aData['idBku'] + "?tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editStLs = "<a href='" + getbasepath() + "/bku/editbkuls/" + aData['kodeTransaksi'] + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editStBL = "<a href='" + getbasepath() + "/bku/editbkustbl/" + aData['kodeTransaksi'] + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editStLs2 = "<a href='" + getbasepath() + "/bku/editbkuls2/" + aData['kodeTransaksi'] + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editStBTL = "<a href='" + getbasepath() + "/bku/editbkustbtl/" + aData['kodeTransaksi'] + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editspj = "<a href='" + getbasepath() + "/bku/editbkuspj/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editspjkoreksi = "<a href='" + getbasepath() + "/bku/editbkuspjkoreksi/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editCek = "<a href='" + getbasepath() + "/bku/editbkucek/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editSp2d = "<a href='" + getbasepath() + "/bku/editbkusp2d/" + aData['idTransaksi'] + "/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editNPD = "<a href='" + getbasepath() + "/bku/editbkunpd/" + aData['kodeTransaksi'] + "?nobku=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editLainLain = "<a href='" + getbasepath() + "/bku/editbkulainlain/" + aData['idBku'] + "?tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editKasMasuk = "<a href='" + getbasepath() + "/bku/editbkukasmasuk/" + aData['idBku'] + "?tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editpajakkoreksi = "<a href='" + getbasepath() + "/bku/editbkupajakkoreksi/" + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editjasagiro = "<a href='" + getbasepath() + "/bku/editbkujasagiro/" + aData['idBku'] + "?tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editbpjs = "<a href='" + getbasepath() + "/bku/editbkubpjs/" + aData['idBku'] + "?tahun=" + tahun + "' class='icon-edit' ></a>-";
                
                var monitoring = "<a href='" + getbasepath() + "/bku/monitoringbku/" + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringStUp = "<a href='" + getbasepath() + "/bku/monitoringbkustup/" + aData['idBku'] + "?tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringStLs = "<a href='" + getbasepath() + "/bku/monitoringbkuls/" + aData['kodeTransaksi'] + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringStLs2 = "<a href='" + getbasepath() + "/bku/monitoringbkuls2/" + aData['kodeTransaksi'] + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringspj = "<a href='" + getbasepath() + "/bku/monitoringbkuspj/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringspjkoreksi = "<a href='" + getbasepath() + "/bku/monitoringbkuspjkoreksi/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringCek = "<a href='" + getbasepath() + "/bku/monitoringbkucek/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringSp2d = "<a href='" + getbasepath() + "/bku/monitoringbkusp2d/" + aData['idTransaksi'] + "/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringNPD = "<a href='" + getbasepath() + "/bku/monitoringbkunpd/" + aData['kodeTransaksi'] + "?nobku=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringLainLain = "<a href='" + getbasepath() + "/bku/monitoringbkulainlain/" + aData['idBku'] + "?tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringKasMasuk = "<a href='" + getbasepath() + "/bku/monitoringbkukasmasuk/" + aData['idBku'] + "?tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringpajak = "<a href='" + getbasepath() + "/bku/monitoringbkupajak/" + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringjasagiro = "<a href='" + getbasepath() + "/bku/monitoringbkujasagiro/" + aData['idBku'] + "?tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringbpjs = "<a href='" + getbasepath() + "/bku/monitoringbkubpjs/" + aData['idBku'] + "?tahun=" + tahun + "' class='icon-file-text' ></a>";
                
                var tanggalDok;
                var editvalid;
                var monitor;

                if (index == 1) {
                    tanggalDok = "";
                } else {
                    tanggalDok = tglPost;
                }
                
                if (aData['ketKegiatan'] == null) {
                    kegiatan = "";
                }

                if (index == 1 || aData['bkuStatus'] == 1 || (aData['noJournal'] !== null && aData['kodeTransaksi'] !== "JO")) {
                    editvalid = "";

                } else if (aData['kodeTransaksi'] == "NM" || aData['kodeTransaksi'] == "NP") {

                    editvalid = editNPD;

                } else if (aData['kodeTransaksi'] == "JO" || aData['kodeTransaksi'] == "JL") {
                    editvalid = editSp2d; // hanya bisa edit data filling saja

                } else if ((aData['kodeTransaksi'] == "ST") && aData['beban'] == "UP") {
                    if (aData['kodeKoreksi'] == "0"){
                        editvalid = editStUp; // edit Setoran UP - tanpa grid
                    } else {
                        editvalid = "";
                    }
                    
                    kegiatan = "";

                } else if (aData['kodeTransaksi'] == "JG") {
                    console.log("kode trx JG, kode koreksi = "+aData['kodeKoreksi']);
                    if (aData['kodeKoreksi'] == "0"){
                        editvalid = editjasagiro; // jasa giro
                    } else {
                        editvalid = "";
                    }
                    
                    kegiatan = "";

                } else if (aData['kodeTransaksi'] == "BP") {
                    if (aData['kodeKoreksi'] == "0"){
                        editvalid = editbpjs; // jasa giro
                    } else {
                        editvalid = "";
                    }
                    
                    kegiatan = "";

                } else if (aData['kodeTransaksi'] == "SB") {
                    if (aData['jenis'] == 1) {
                        editvalid = editStLs2; // edit Setoran LS - kegiatan

                    } else if (aData['jenis'] == 3) {
                        editvalid = editStLs2;// kegiatan pindah ke atas //editStLs; // edit Setoran LS + kegiatan

                    }

                } else if (aData['kodeTransaksi'] == "C1" || aData['kodeTransaksi'] == "C2") {

                    editvalid = editCek;
                    kegiatan = "";

                } else if (aData['kodeTransaksi'] == "ST" && aData['beban'] == "TU") {
                    editvalid = editStBL; // edit Setoran LS + kegiatan

                } else if (aData['kodeTransaksi'] == "ST" && aData['beban'] == "LS") {
                    if (aData['jenis'] == 1) {
                        editvalid = editStBTL; // edit Setoran LS - kegiatan

                    } else if (aData['jenis'] == 4) {
                        editvalid = editStLs2; // edit Setoran LS - kegiatan

                    } else if (aData['jenis'] == 2) {
                        editvalid = editStLs; // edit Setoran LS - kegiatan

                    } else if (aData['jenis'] == 3) {
                        editvalid = editStBL; // edit Setoran LS + kegiatan

                    }

                } else if (aData['kodeTransaksi'] == "LL") {
                    editvalid = editLainLain;

                } else if (aData['kodeTransaksi'] == "KM") {
                    editvalid = editKasMasuk;

                } else if (aData['kodeTransaksi'] == "JJ") {
                    if (aData['kodeKoreksi'] == "1") {
                        editvalid = editspjkoreksi; // edit SPJ Koreksi

                    } else {
                        editvalid = editspj; // edit SPJ

                    }

                } else if (aData['kodeTransaksi'] == "P1" || aData['kodeTransaksi'] == "P2" || aData['kodeTransaksi'] == "P3" || aData['kodeTransaksi'] == "P4" || aData['kodeTransaksi'] == "P5" || aData['kodeTransaksi'] == "P6") {
                    if (aData['idBas'] == 0) { // Untuk PJ
                        uraian = "<textarea id='kegiatan" + index + "'readonly style='border:none;margin:0;width:200px;'>" + "" + "</textarea>";
                    }

                    if (aData['kodeKoreksi'] == "1") {
                        editvalid = editpajakkoreksi; // edit Pajak Koreksi
                    } else {
                        editvalid = editpajak; // edit Pajak
                    }
                }

                if (index == 1) {
                    monitor = "";

                } else if (aData['kodeTransaksi'] == "NM" || aData['kodeTransaksi'] == "NP") {

                    monitor = monitoringNPD;

                } else if (aData['kodeTransaksi'] == "JO" || aData['kodeTransaksi'] == "JL") {
                    monitor = monitoringSp2d;

                } else if ((aData['kodeTransaksi'] == "ST") && aData['beban'] == "UP") {
                    monitor = monitoringStUp;

                } else if (aData['kodeTransaksi'] == "JG")  {
                    monitor = monitoringjasagiro;

                } else if (aData['kodeTransaksi'] == "BP")  {
                    monitor = monitoringbpjs;

                } else if (aData['kodeTransaksi'] == "SB") {
                    if (aData['jenis'] == 1) {
                        monitor = monitoringStLs2;

                    } else if (aData['jenis'] == 3) {
                        monitor = monitoringStLs;
                    }

                } else if (aData['kodeTransaksi'] == "C1" || aData['kodeTransaksi'] == "C2") {
                    monitor = monitoringCek;

                } else if (aData['kodeTransaksi'] == "ST" && aData['beban'] == "TU") {
                    monitor = monitoringStLs;
                } else if (aData['kodeTransaksi'] == "ST" && aData['beban'] == "LS") {
                    if (aData['jenis'] == 1 || aData['jenis'] == 4) {
                        monitor = monitoringStLs2;

                    } else if (aData['jenis'] == 2 || aData['jenis'] == 3) {
                        monitor = monitoringStLs;
                    }

                } else if (aData['kodeTransaksi'] == "LL") {
                    monitor = monitoringLainLain;

                } else if (aData['kodeTransaksi'] == "KM") {
                    monitor = monitoringKasMasuk;

                } else if (aData['kodeTransaksi'] == "JJ") {
                    if (aData['kodeKoreksi'] == "1") {
                        monitor = monitoringspjkoreksi;
                    } else {
                        monitor = monitoringspj;
                    }

                } else if (aData['kodeTransaksi'] == "P1" || aData['kodeTransaksi'] == "P2" || aData['kodeTransaksi'] == "P3" || aData['kodeTransaksi'] == "P4" || aData['kodeTransaksi'] == "P5" || aData['kodeTransaksi'] == "P6") {
                    monitor = monitoringpajak;
                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(tanggalDok);
                $('td:eq(6)', nRow).html(uraian);
                $('td:eq(7)', nRow).html(kegiatan);
                $('td:eq(8)', nRow).html(nilaimasuk + idtrans);
                $('td:eq(9)', nRow).html(nilaikeluar);
                $('td:eq(10)', nRow).html(saldokas);
                $('td:eq(11)', nRow).html(editvalid + monitor);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, "sWidth": "5%", sClass: "center"},
                {"mDataProp": "tglDok", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBKU", "bSortable": true, sClass: "center"},
                {"mDataProp": "noBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "beban", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeakun", "bSortable": false, sClass: "center"},
                {"mDataProp": "uraianBukti", "bSortable": false, sClass: "left"},
                {"mDataProp": "ketKegiatan", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiMasuk", "bSortable": false, sClass: "right"},
                {"mDataProp": "nilaiKeluar", "bSortable": false, sClass: "right"},
                {"mDataProp": "saldoKas", "bSortable": false, "sWidth": "18%", sClass: "right"},
                {"mDataProp": "idBas", "bSortable": false, "sWidth": "5%", sClass: "center"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function getTotal() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var bulan = $('#bulan').val();

    $.getJSON(getbasepath() + "/bku/json/getTotal", {tahun: tahun, idskpd: idskpd, bulan: bulan},
    function(result) {

        var tm = result.aData[0]['nilaiMasuk'];
        var tk = result.aData[0]['nilaiKeluar'];
        var ts = result.aData[0]['nilaiSisa'];

        $("#totmasuk").val(accounting.formatNumber(tm, 2, '.', ","));
        $("#totkeluar").val(accounting.formatNumber(tk, 2, '.', ","));
        $("#totsaldokas").val(accounting.formatNumber(ts, 2, '.', ","));
        $("#saldokas").val(accounting.formatNumber(ts, 2, '.', ","));

    });
}

function getWilayah() {

    $.getJSON(getbasepath() + "/bku/json/getWilayahByLogin", {},
            function(result) {

                var kode = result.aData[0]['kodeWilayah'];
                var ket = result.aData[0]['ketWilayah'];
                $("#wilayah").val("  " + ket);

            });

}