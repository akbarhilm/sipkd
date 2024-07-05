$(document).ready(function() {
    //bootbox.alert("kode group = "+$('#kodegrup').val()+ " || idskpd = " +$('#idskpd').val());
    document.getElementById("labelproses").style.display = "none";
    $('#btnproses').attr("disabled", true);
});

var keterangan = "---";
var kodeProses;

function gridsp2d() {
    //bootbox.alert("tgl sah == "+ $('#tglsah').val());
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
            "sAjaxSource": getbasepath() + "/journalsp2d/json/journalsp2d",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                //formatnumberonkeyup();
                //$(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "tanggal", value: $('#tglsah').val()},
                {name: "wilayah", value: $('#wilayah').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "kodegrup", value: $('#kodegrup').val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {

                if (aaData.length > 0) {
                    $('#btnproses').attr("disabled", false);
                    $('#buttoninduk').attr("disabled", false);
                } else {
                    $('#btnproses').attr("disabled", true);
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

        $("#tglsah").html(opt);
        gridsp2d();
        getKodeProsesJour();

    });

}

function cek() {
    console.log("masuk function cek()");
}

function simpan() {
    document.getElementById("labelproses").style.display = "block";
    document.getElementById("btnproses").style.display = "none";
    $('#btnproses').attr("disabled", true);

    var tahun = $("#tahun").val();
    var idskpd = $("#idskpd").val();


    //if(kodestatus == "1"){
    //bootbox.alert("Data LRA Sudah Pernah Diroses.");
    // } else {
    var varurl = getbasepath() + "/journalsp2d/json/prosessimpanjurnal";
    var dataac = [];

    var datajour = {
        tahun: tahun,
        idskpd: idskpd,
        wilayah: $('#wilayah').val(),
        tglsah: $('#tglsah').val(),
        keterangan: keterangan
    }
    dataac = datajour;

    return   $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {
        setTanggal($('#wilayah').val());
        document.getElementById("labelproses").style.display = "none";
        $('#btnproses').attr("disabled", false);
        document.getElementById("btnproses").style.display = "block";
        bootbox.alert("Jurnal Berhasil Disimpan");
        gridsp2d();
    });
    //}

}


function cekJourSp2d() {
    var tahun = $('#tahun').val();
    var wilayah = $('#wilayah').val();
    var tglsah = $('#tglsah').val();

    $.getJSON(getbasepath() + "/journalsp2d/json/getBanyakSp2dJour", {tahun: tahun, tglsah: tglsah, wilayah: wilayah},
    function(result) {
        console.log("result cek Jour Sp2d = " + result);

        if (result == 0) {
            simpan();
        } else {
            if (kodeProses == "0") { // JIKA PROSES JURNAL YG PERTAMA BELUM SELESAI
                bootbox.alert("Proses Jurnal Sebelumnya Belum Selesai");
            } else {
                bootbox.confirm("SP2D Sudah Jurnal untuk Tanggal " + $('select[name="tglsah"]').find(":selected").text() + ". Lakukan Proses Jurnal Ulang ?", function(result) {
                    if (result) {
                        return   $.ajax({
                            //type: "POST",
                            //url: "",//getbasepath() + "/journalsp2d/json/hapussp2dcetak",
                            contentType: "text/plain; charset=utf-8",
                            headers: {
                                'Accept': 'application/json',
                                'Content-Type': 'application/json'
                            },
                            data: JSON.stringify()
                        }).always(function(data) {
                            keterangan = "JURNAL ULANG";
                            console.log("Masuk ke jurnal ulang");
                            simpan();
                        });
                    } else {
                        bootbox.hideAll();
                        return result;
                    }
                });
            }
        }

    });
}

function getKodeProsesJour() {

    var tahun = $('#tahun').val();
    var tanggal = $('#tglsah').val();
    var wilayah = $('#wilayah').val();

    $.getJSON(getbasepath() + "/journalsp2d/json/getKodeProsesJour", {tglsah: tanggal, tahun: tahun, kodewil: wilayah},
    function(result) {
        //console.log(result);
        kodeProses = result.aData['kodeProsesJour'];

    });

}