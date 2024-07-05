$(document).ready(function() {
    gridterimaspm();
    setButton();
    validasisimpan();
    getDataBankDki();
});

var statusbankdki;
var response76 = "00";

function cari() {

    $("#kodeskpdfilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridterimaspm();
        }
    });

}
function cari2() {

    $("#namaskpdfilter").keyup(function() {
        var panjang = $(this).val().length;
        if (panjang > 2 || panjang == 0) {
            gridterimaspm();
        }
    });
}


function getDataBankDki() {
    var kodebank = $('#kodeBank').val();
    var kodeVA = $('#kodeVA').val();
    var norek = $('#noRekening').val();
    norek = norek.replace(/[-., *+?^${}()/|[\]\\]/g, "");

    console.log("norek = " + norek);
    console.log("kodeVA = " + kodeVA);


    if (norek !== "" && kodeVA == "0" && kodebank !== "001") {
        //var varurl = getbasepath() + "/postdata/json/kirimpostdata";
        var varurl = getbasepath() + "/halamanpostdata/json/ceknorek";

        var dataac = [];

        var datajour = {
            kodebank: kodebank,
            norek: norek
        };
        dataac = datajour;

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: varurl,
            data: JSON.stringify(dataac),
            success: function(data) {
                //console.log(data);

                if (kodebank == '111') {
                    $('#dkinamabank').val(data.namabank);
                    $('#dkikodebank').val(data.kodebank);
                    $('#dkinorek').val(data.norek);
                    $('#dkialamat').val(data.alamat);
                    $('#dkinama').val(data.nama);
                    $('#dkinpwp').val(data.npwp);

                } else {
                    $('#dkinamabank').val(data["bank"]);
                    $('#dkikodebank').val(data["code"]);
                    $('#dkinorek').val(data["account"]);
                    $('#dkinama').val(data["name"]);
                    $('#dkialamat').val("");
                    $('#dkinpwp').val("");
                    response76 = data["response"];
                }

            },
            error: function(xhr) {
                console.error(xhr);
            }
        }).always(function(data) {
            cekDataBank();
        });
    } else {
        $("#buttonsimpan").attr('disabled', false);
    }

    /* if (kodebank == "111" && kodeVA == "0") {
     var varurl = getbasepath() + "/spmterima/json/kirimpostdata";

     var dataac = [];

     var datajour = {
     kodebank: $('#kodeBank').val(),
     norek: $('#noRekening').val()
     };
     dataac = datajour;

     $.ajax({
     type: 'POST',
     contentType: 'application/json',
     url: varurl,
     data: JSON.stringify(dataac),
     success: function(data) {
     console.log(data);

     $('#dkinamabank').val(data.namabank);
     $('#dkikodebank').val(data.kodebank);
     $('#dkinorek').val(data.norek);
     $('#dkialamat').val(data.alamat);
     $('#dkinama').val(data.nama);
     $('#dkinpwp').val(data.npwp);

     },
     error: function(xhr) {
     console.error(xhr);
     }
     }).always(function(data) {

     cekDataBank();
     });

     } else {
     $("#buttonsimpan").attr('disabled', false);
     }
     */

}

function cekDataBank() {
    var datasama = true;
    var keterangan = "Data ";

    var kodebank = $('#kodeBank').val();

    if ($('#dkikodebank').val() !== $('#kodeBank').val()) {
        datasama = false;
        keterangan = keterangan + "Kode Bank";
    }

    if (($('#dkinamabank').val() !== $('#namaBank').val()) && ($('#kodeBank').val() !== "111")) {
        datasama = false;
        if (keterangan == "Data ") {
            keterangan = keterangan + "Nama Bank";
        } else {
            keterangan = keterangan + " , Nama Bank";
        }

    }

    if ($('#dkinorek').val() !== $('#noRekening').val()) {
        datasama = false;
        if (keterangan == "Data ") {
            keterangan = keterangan + "No Rekening";
        } else {
            keterangan = keterangan + " , No Rekening";
        }

    }
    var panjangnamadki = $("#dkinama").val().trim().length;
    if ($('#dkinama').val().trim() !== $('#namaTujuan').val().substr(0, panjangnamadki)) {
        datasama = false;
        console.log("dki trim : " + $("#dkinama").val().trim());
        console.log("nama 30 : " + $('#namaTujuan').val().substr(0, 30));
        console.log("nama panjangnamadki : " + $('#namaTujuan').val().substr(0, panjangnamadki));
        console.log("dki trim length : " + $("#dkinama").val().trim().length);

        if (keterangan == "Data ") {
            keterangan = keterangan + "Nama Tujuan";
        } else {
            keterangan = keterangan + " , Nama Tujuan";
        }

    }

    if ($('#kodeBank').val() == "001") {
        datasama = true;
    }

    if (datasama == false) {
        bootbox.alert(keterangan + " Tidak Sama dengan Data Bank DKI.");
        $("#buttonsimpan").attr('disabled', true);
        statusbankdki = false;
    } else {
        $("#buttonsimpan").attr('disabled', false);
        statusbankdki = true;
    }

}

/*
 function getJSONP(url, success) {

 var ud = '_' + +new Date,
 script = document.createElement('script'),
 head = document.getElementsByTagName('head')[0]
 || document.documentElement;

 window[ud] = function(data) {
 head.removeChild(script);
 success && success(data);
 };

 script.src = url.replace('callback=?', 'callback=' + ud);
 head.appendChild(script);

 }
 */



function setButton() {
    if ($('#namaPegTerimakpkd').val() === "" || $('#namaPegTerimakpkd').val() === null
            || $('#namaPegPemberiskpd').val() === "" || $('#namaPegPemberiskpd').val() === null) {
        $("#buttonsimpan").attr('disabled', false);
        $("#buttonbatal").attr('disabled', true);
        $("#buttoncetak").attr('disabled', true);

    } else {
        $("#buttonsimpan").attr('disabled', true);
        $("#buttonbatal").attr('disabled', false);
        $("#buttoncetak").attr('disabled', false);
    }

}

function validasisimpan() {
    //if (statusbankdki == true) {
    if ($('#namaPegTerimakpkd').val() === "" || $('#namaPegTerimakpkd').val() === null
            || $('#namaPegPemberiskpd').val() === "" || $('#namaPegPemberiskpd').val() === null) {
        $("#buttonsimpan").attr('disabled', true);

    } else {
        $("#buttonsimpan").attr('disabled', false);
    }
    /* } else {
     $("#buttonsimpan").attr('disabled', true);
     } */

    var kodebank = $('#kodeBank').val();
    var kodebankdki = $('#dkikodebank').val();

    var kodeVA = $('#kodeVA').val();

    if (kodebank == "111" && kodeVA == "0" && (kodebankdki == "")) {
        $("#buttonsimpan").attr('disabled', true);
    }


}

function gridterimaspm() {
    var baseurl = getbasepath();
    var urljson = baseurl + "/spmterima/json/getlistspmterima";
    if (typeof myTable === 'undefined') {
        myTable = $('#terimaspmtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sDom": '<"top"i>rt<"bottom"flp><"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                //{name: "codeWilSp2dproses", value: $('#codeWilSp2dproses').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "nospmfilter", value: $("#nospmfilter").val()},
                {name: "kodeskpdfilter", value: $("#kodeskpdfilter").val()},
                {name: "namaskpdfilter", value: $("#namaskpdfilter").val()}

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
                var codeWilSp2dproses;
                switch (aData['codeWilSp2dproses']) {
                    case "1" :
                        codeWilSp2dproses = "Jakarta Pusat";
                        break;
                    case "2" :
                        codeWilSp2dproses = "Jakarta Utara";
                        break;
                    case "3" :
                        codeWilSp2dproses = "Jakarta Barat";
                        break;
                    case "4" :
                        codeWilSp2dproses = "Jakarta Selatan";
                        break;
                    case "5" :
                        codeWilSp2dproses = "Jakarta Timur";
                        break;
                    case "6" :
                        codeWilSp2dproses = "Kepulauan Seribu";
                        break;
                    case "0" :
                        codeWilSp2dproses = "Abdul Muis";
                        break;
                }
                var pilih = "<a class='icon-edit' href='" + getbasepath() + "/spmterima/insertspmterima/" + aData['idSpmCetak'] + "'  > </a>";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(codeWilSp2dproses);
                $('td:eq(10)', nRow).html(pilih);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpmCetak", "bSortable": false, sClass: "center"},
                {"mDataProp": "codeWilSp2dproses", "bSortable": false, sClass: "center"},
                {"mDataProp": "iSpmno", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "iSpmnoDok", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "codeJenis", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "codeBeban", "bSortable": false, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "codeSkpd", "bSortable": false, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "namaSkpd", "bSortable": false, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "dSpmCetak", "bSortable": false, sClass: "center"},
                {"mDataProp": "eSpm", "bSortable": false, sClass: "left"},
                {"mDataProp": "idSpmCetak", "bSortable": false, sClass: "center", sDefaultContent: "-"}
            ]
        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


function cetakjurnal() {
    var idSpmCetak = $('#idSpmCetak').val();
    var tahun = $("#tahun").val();
    window.location.href = getbasepath() + "/spmterima/json/prosescetak?tahun=" + tahun + "&idSpmCetak=" + idSpmCetak;
}

function batalspm() {
    var idSpmCetak = $('#idSpmCetak').val();
    bootbox.confirm("Are you sure you want to Delete?", function(result) {
        if (result)
            window.location.href = getbasepath() + "/spmterima/delspmterima/" + idSpmCetak;
    });
}

function simpanspm() {
    window.location.href = getbasepath() + "/spmterima/prosesinsert";
}

/*
 * Fungsi untuk validasi batal terima spm, jika sudah ada data spm di tmsp2d maka tidak bisa batal
 */
function cekJurnal() {
    var idSpmCetak = $('#idSpmCetak').val();

    $.getJSON(getbasepath() + "/spmterima/json/getSPMBelumSP2D", {idSpmCetak: idSpmCetak},
    function(result) {

        var banyak = result.aData.length;
        console.log("banyak anita" + banyak);
        if (banyak > 0) { // jika datanya ada / tidak bisa batal
            bootbox.alert({//tampilkan peringatan tanggal berapa yg belum dijurnal
                title: "Data SPM terima tidak bisa dibatalkan.",
                message: "Data SPM cetak telah dilakukan proses SP2D.",
                callback: function() {
                    $.fancybox.close();
                }
            });
        }
        else {
            bootbox.confirm("Are you sure you want to Delete?", function(result) {
                if (result)
                    window.location.href = getbasepath() + "/spmterima/delspmterima/" + idSpmCetak;
            });
        }
    });
}

function cek() {
    var panjangnamadki = $("#dkinama").val().trim().length;
    var namatujuan;
    if ($("#kodeBank").val() == "111") {
        namatujuan = $("#namaTujuan").val();
    } else {
        namatujuan = $("#namaTujuan").val().substr(0, panjangnamadki); //$("#namaTujuan").val().substr(0, 30);
    }

    //if ($("#kodeBank").val() == "111" && $('#kodeVA').val() == "0") {
    if ($('#kodeVA').val() == "0" && $("#kodeBank").val() != "001") { // jika Bank Indonesia(001)
        if ($("#noRekening").val() !== $("#dkinorek").val()) {
            bootbox.alert("Cek Koneksi ke Bank DKI");
            return false;

        } else if ($("#dkinama").val() == "GENERAL ERROR" || $("#dkinorek").val() == "GENERAL ERROR") {
            bootbox.alert("Cek Koneksi ke Bank DKI");
            return false;

        } else if ($("#dkinama").val() == "rekening Tidak Ada Pada Master/rekening Tidak Aktif") {
            bootbox.alert("Rekening Tidak Ada Pada Master/Rekening Tidak Aktif");
            return false;

        } else if ($("#dkinama").val().trim() !== namatujuan) {
            /*console.log("dki trim : "+$("#dkinama").val().trim());
             console.log("nama 30 : "+namatujuan);
             console.log("dki trim length : "+$("#dkinama").val().trim().length());*/
            bootbox.alert("Nama Tujuan Harus Sama dengan Hasil Inquiry Bank DKI");
            return false;

        }

    } else {
        return true;
    }


}
