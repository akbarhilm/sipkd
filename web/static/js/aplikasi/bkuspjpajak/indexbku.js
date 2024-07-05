$(document).ready(function() {
    getWilayah();
    setBulan();
    gridbku();

    if ($("#idskpd").val() == "761" || $("#idskpd").val() == "1234") {
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
            "sAjaxSource": getbasepath() + "/bkuspjpajak/json/listindex",
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
                //if (index > 1) {
                tahun = dpost.substr(0, 4);
                mm = dpost.substr(4, 2);
                dd = dpost.substr(6, 2);
                tglPost = dd + "/" + mm + "/" + tahun;
                //}
                var nobukti = aData['noBukti'];

                var nilaim = accounting.formatNumber(aData['nilaiMasuk'], 2, '.', ",");
                var nilaik = accounting.formatNumber(aData['nilaiKeluar'], 2, '.', ",");

                var nilaimasuk = "<input type='text' name='nilaimasuk" + index + "' id='nilaimasuk" + index + "'  class='inputmoney'  value='" + nilaim + "' readOnly='true' />";
                var nilaikeluar = "<input type='text' name='nilaikeluar" + index + "' id='nilaikeluar" + index + "'  class='inputmoney'  value='" + nilaik + "' readOnly='true'/>";
                var idtrans = "<input type='hidden' id='kodetransaksi" + index + "' value='" + aData['idTransaksi'] + "' />";
                var uraian = "<textarea id='uraianbukti" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['uraianBukti'] + "</textarea>";
                var kegiatan = "<textarea id='kegiatan" + index + "'readonly style='border:none;margin:0;width:200px;'>" + aData['ketKegiatan'] + "</textarea>";
                var edit = "<a href='" + getbasepath() + "/bkuspjpajak/editbkuspj/" + "?tahun=" + tahun + "&nobukti=" + nobukti + "&tglpost=" + dpost + "' class='icon-edit' ></a>-";
                var editpajak = "<a href='" + getbasepath() + "/bku/editbkupajak/" + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-edit' ></a>-";
                var editspj = "<a href='" + getbasepath() + "/bku/editbkuspj/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-edit' ></a>";

                var monitoringpajak = "<a href='" + getbasepath() + "/bku/monitoringbkupajak/" + "?noBKU=" + aData['noBKU'] + "&tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringspj = "<a href='" + getbasepath() + "/bku/monitoringbkuspj/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitoringspjkoreksi = "<a href='" + getbasepath() + "/bku/monitoringbkuspjkoreksi/" + aData['noBKU'] + "?tahun=" + tahun + "' class='icon-file-text' ></a>";
                var monitor = "";

                if (aData['kodeTransaksi'] == "JJ") {
                    if (aData['kodeKoreksi'] == "1") {
                        monitor = monitoringspjkoreksi;
                    } else {
                        monitor = monitoringspj;
                    }

                } else if (aData['kodeTransaksi'] == "P1" || aData['kodeTransaksi'] == "P2" || aData['kodeTransaksi'] == "P3" || aData['kodeTransaksi'] == "P4" || aData['kodeTransaksi'] == "P5" || aData['kodeTransaksi'] == "P6") {
                    monitor = monitoringpajak;
                }

                var tanggalDok;
                var editvalid;


                tanggalDok = tglPost;

                if (aData['ketKegiatan'] == null) {
                    kegiatan = "";
                }

                if (aData['bkuStatus'] == 1 || (aData['noJournal'] !== null && aData['kodeTransaksi'] !== "JO")) {
                    editvalid = "";

                } else {

                    editvalid = edit;

                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(tanggalDok);
                $('td:eq(6)', nRow).html(uraian);
                $('td:eq(7)', nRow).html(kegiatan);
                $('td:eq(8)', nRow).html(nilaimasuk + idtrans);
                $('td:eq(9)', nRow).html(nilaikeluar);
                $('td:eq(10)', nRow).html(editvalid+monitor);

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

function getWilayah() {

    $.getJSON(getbasepath() + "/bku/json/getWilayahByLogin", {},
            function(result) {

                var kode = result.aData[0]['kodeWilayah'];
                var ket = result.aData[0]['ketWilayah'];
                $("#wilayah").val("  " + ket);

            });

}