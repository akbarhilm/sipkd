$(document).ready(function() {
    //bootbox.alert("kode group = "+$('#kodegrup').val()+ " || idskpd = " +$('#idskpd').val());
    $('#buttoninduk').attr("disabled", true);
});

function setTglSah(){
    var tglawal = $("#tanggalAwal").val();
    var tglakhir = $("#tanggalAkhir").val();
    var d,m,y,dd, mm, yy,tglgabawal, tglgabakhir;

    dd = tglawal.substr(0, 2);
    mm = tglawal.substr(3, 2);
    yy = tglawal.substring(6);
    tglgabawal = yy + mm + dd;
    
    d = tglakhir.substr(0, 2);
    m = tglakhir.substr(3, 2);
    y = tglakhir.substring(6);
    tglgabakhir = y + m + d;
    
    if ($("#tanggalAwal").val() == ""){
        tglgabawal = "00000000";
    }
    
    if ($("#tanggalAkhir").val() == ""){
        tglgabakhir = "00000000";
    }
    
    if (tglgabawal !== "00000000" && tglgabakhir !== "00000000"){
        $('#buttoninduk').attr("disabled", false);
    } 
    
    $("#tglsah").val(tglgabawal);
    $("#tglsahakhir").val(tglgabakhir);
    
    //console.log("tglsah == "+ tglgabawal);
    //console.log("tglsahakhir == "+ tglgabakhir);
    
}

function gridsp2d( ) {
    if (typeof myTable == 'undefined') {
        myTable = $('#jourskpdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/journalsp2d/json/journalsp2dAll",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                //formatnumberonkeyup();
                //$(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "tanggalawal", value: $('#tglsah').val()},
                {name: "tanggalakhir", value: $('#tglsahakhir').val()},
                {name: "wilayah", value: $('#wilayah').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "kodegrup", value: $('#kodegrup').val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

                if (aaData.length > 0) {
                    $('#buttoninduk').attr("disabled", false);
                } else {
                    $('#buttoninduk').attr("disabled", true);
                }
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
                var nilaiD = aData['skpdket'];
                var nilaiK = aData['kode'];
                var nilaidebtext = "<input type='text' name='nilaidebet" + aData['idsp2d'] + "' id='nilaidebet" + aData['idsp2d'] + "'  style='text-align: right'   class='inputmoney'  value='" + nilaiD + "' onkeyup='hitungGrandtotalD(" + index + ",this.value),validasiDebetNR(" + index + ",this.value)' onclick = 'validasiDebetKlik(" + index + ")' />"
                var nilaikretext = "<input type='text' name='nilaikredit" + aData['idSkpd'] + "' id='nilaikredit" + aData['idSkpd'] + "'  style='text-align: right'   class='inputmoney'  value='" + nilaiK + "' onkeyup='hitungGrandtotalK(" + index + ",this.value),validasiKreditNR(" + index + ",this.value)' onclick = 'validasiKreditKlik(" + index + ")'/>"

                var nilai = accounting.formatNumber(aData['nilai']);
                var inputnilai = "<input id='nilai" + index + "' class='inputmoney' name='nilai" + index + "' value='" + nilai + "'  readOnly='true'   />";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(4)', nRow).html(inputnilai);
                //$('td:eq(4)', nRow).html(nilaidebtext);
                //$('td:eq(5)', nRow).html(nilaikretext);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idsp2d", "sWidth": "5%", "bSortable": false, sClass: "center"},
                {"mDataProp": "nosp2d", "sWidth": "15%", "bSortable": false, sClass: "center"},
                {"mDataProp": "skpdket", "sWidth": "35%", "bSortable": false, sClass: "left"},
                {"mDataProp": "kode", "sWidth": "25%", "bSortable": false, sClass: "center"},
                {"mDataProp": "nilai", "sWidth": "20%", "bSortable": false, sClass: "kanan"}

            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function setWilayahAll() {

    $.getJSON(getbasepath() + "/journalsp2d/json/setWilayah", {},
            function(result) {
                //console.log(result);

                var banyak, qkode, qket;
                var opt;
                var kode = "ALL";
                var ket = "Semua Wilayah";
                opt = '<option value="' + kode + '" selected>' + ket + '</option>';

                banyak = result.aData.length;

                if (banyak > 0) {
                    for (var i = 0; i < banyak; i++) {
                        qkode = result.aData[i]['kodewil'];
                        qket = result.aData[i]['ketwilayah'];

                        opt += '<option value="' + qkode + '">' + qket + '</option>';
                    }
                }

                $("#wilayah").html(opt);
                
            });
    
}

function setTanggal(kodewil) {
    var kodegrup = $('#kodegrup').val();
    var idskpd = $('#idskpd').val();
    
    
    $.getJSON(getbasepath() + "/journalsp2d/json/setTanggal", {kodewil: kodewil, kodegrup: kodegrup, idskpd: idskpd},
    function(result) {
        //console.log(result);

        var banyak, kode, ket;
        var opt = "";

        banyak = result.aData.length;

        if (banyak > 0) {
            for (var i = 0; i < banyak; i++) {
                kode = result.aData[i]['kodetgl'];
                ket = result.aData[i]['kettgl'];

                opt += '<option value="' + kode + '">' + ket + '</option>';
            }
        }

        $("#daftarTgl").html(opt);
        //gridsp2d();

    });

}