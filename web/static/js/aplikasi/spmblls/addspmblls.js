$(document).ready(function() {
    //$("#tglSpm").datepicker("setDate", new Date());
    if ($("#idskpd").val()) {
        // getpagudanspd($("#idskpd").val())
    }
    setCurrentDate();

    getAkunNihil();
    getSppUangMuka();

    // untuk sementara, karena VA belum di deploy ke produksi, jadi edit data pfk ga pek edit VA dulu

    //document.getElementById("labelVA").style.display = "none"; // tdk tampil

    //
});

var nilaisppakun, response76;

function setCurrentDate() {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    var yy = today.getFullYear();

    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }

    var bulan = dd + "/" + mm + "/" + yy;

    $('#tglSpm').val(bulan);

}

function getpagudanspd(idskpd) {
    $.getJSON(getbasepath() + "/spmblls/json/getanggarandanspdbantuanlangsung/" + idskpd,
            function(result) {
                $('#totalAngaran').val(accounting.formatNumber(result.anggaran));
                $('#nilaiSpd').val(accounting.formatNumber(result.spd));
            });

}

function getbanyakspdrinci( ) {
    $.getJSON(getbasepath() + "/sppup/json/getlistspdblbanyak", {tahunAnggaran: $("#tahun").val(), idskpd: $("#idskpd").val()},
    function(result) {
        $('#banyakrinci').val(result);
    });

}

function pindahhalamanadepan() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spmup/indexspmup/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spmup/indexspmup");
    }

}

function cekall(obj) {
    if ($(obj).is(":checked")) {
        $("#spprincitable :input[type='text']").attr("readonly", false);
    } else {
        $("#spprincitable :input[type='text']").attr("readonly", "readonly");
    }
}

function gridspprinci() {
    var urljson = getbasepath() + "/spmblls/json/getlistspdbl";
    if (typeof myTable == 'undefined') {
        myTable = $('#spprincitable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "autoWidth": false,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sDom": '<"toolbar">flrtip',
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "iDisplayLength": 25,
            "fnDrawCallback": function() {
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
                //  gettotalspddanspp( );
                //  getbanyakspdrinci( );
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "idspp", value: $('#id').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()},
                {name: "spdno", value: $('#nospdfilter').val()},
                {name: "kodekegiatan", value: $('#kodekegiatanfilter').val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                try {

                    var pageTotal = 0;
                    var spptotal = 0;

                    for (var i = iStart; i < iEnd; i++) {
                        pageTotal += parseFloat(aaData[i]['nilaiSpd']);
                        spptotal += parseFloat(aaData[i]['nilaiSpp']);
                        //console.log(spptotal + " = " + aaData[i]['nilaiSpp'])
                    }

                    $("#totalspd").val(accounting.formatNumber(pageTotal, 2, '.', ","));
                    $("#totalspp").val(accounting.formatNumber(spptotal, 2, '.', ","));

                } catch (e) {
                    console.log(e);
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
                var idspd = "<input type='hidden' id='idspd" + index + "'   name='idspd" + index + "'  value='" + aData['idSpd'] + "'  />";
                var nilaispphidden = "<input type='hidden' name='nilaispporg" + index + "'     id='nilaispporg" + index + "' value='" + aData['nilaiSpp'] + "' />";
                var kodeakun = "<input type='hidden' name='kodeakun" + index + "' id='kodeakun" + index + "' value='" + aData['cAkun'] + "' />";
                //var tglspp = getTanggal(aData['tglSpp']);
                var nilaispp = accounting.formatNumber(aData['nilaiSpp'], 2, '.', ",");
                var inputspp = "<input type='text' readonly='true'  id='nilaiSpp" + index + "'    name='nilaiSpp" + index + "'   value='" + nilaispp + "'   class='inputmoney sppnilai'   onkeyup='pasangvalidatebesarperfield(" + index + ");hitungnilaispp()'     />";

                //console.log("kode akun == " + aData['cAkun']);
                //if (aData['cAkun'] == "5.2.2.03.31") {
                if (aData['cAkun'] == "5.2.1.07.01" || aData['cAkun'] == "5.2.2.34.01" || aData['cAkun'] == "5.2.3.33.01") {
                    //console.log("masuk cAkun == BLUD");

                    nilaisppakun = aData['nilaiSpp'];
                    //document.getElementById("potonganUangMuka").disabled = false;
                    //$("#nilaiSpp").val(aData['nilaiSpp']);

                    document.getElementById("potonganUangMuka").disabled = true;
                    document.getElementById("potonganUangMuka").readonly = true;
                    document.getElementById("potongan").disabled = true;
                    document.getElementById("potongan").readonly = true;

                }


                $('td:eq(0)', nRow).html(index + idspd);
                // $('td:eq(2)', nRow).html(tglspp);
                $('td:eq(3)', nRow).html(inputspp + nilaispphidden + kodeakun);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idspp", "bSortable": false, sClass: "center", "sWidth": "4%"},
                {"mDataProp": "cAkun", "bSortable": true, "sWidth": "20%"},
                {"mDataProp": "nAkun", "bSortable": true, "sWidth": "25%"},
                {"mDataProp": "nilaiSpp", "bSortable": false, sClass: "kanan", "sWidth": "20%"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}


//===========================================SPM2===================================================

function getAkunNihil() {
    //$('#id').val()
    $.getJSON(getbasepath() + "/spmblls/json/getAkunNihil", {idspp: $('#id').val()},
    function(result) {

        var banyak = result;

        if (banyak > 0) {
            $("#kodeNihil").val(1);
            $("#nilaiSpp").val(nilaisppakun);
            //getBasNihil();
        } else {
            $("#kodeNihil").val(0);
            $("#nilaiSpp").val(0);
            $('#idbas').val(0);
        }

    });
}

function getBasNihil() {
    var idskpd = $('#idskpd').val();
    //console.log("idskpd == " + idskpd);
    $.getJSON(getbasepath() + "/spmblls/json/getBasNihil", {idskpd: idskpd},
    function(result) {
        var idbas;

        idbas = result.aData['idbas'];
        console.log("idbas result ===== " + idbas);
        $('#idbas').val(idbas);
    });
}

function setnilai() {

    if ($("#kodeNihil").val() == 1) {
        $("#nilaiSpp").val(nilaisppakun);
    } else {
        $("#nilaiSpp").val(0);
    }

}

function getSppUangMuka() {
    var idskpd = $('#idskpd').val();
    var idspp = $('#id').val();
    var idkontrak = $('#idKontrak').val();

    $.getJSON(getbasepath() + "/spmblls/json/getSppUangMuka", {idskpd: idskpd, idspp: idspp, idkontrak: idkontrak},
    function(result) {
        var uangmuka = result.aData['uangmuka'];
        // UNTUK MENCARI TAU APAKAH SPP NYA UMK ATAU BUKAN
        console.log("uangmuka = " + uangmuka);
        /* // ganti jadi baca status uang muka nya..
         if (uangmuka == "1") {
         document.getElementById("potonganUangMuka").disabled = false;
         } else {
         document.getElementById("potonganUangMuka").disabled = true;
         }*/


        gridspprinci();
        getKodePotUmk(uangmuka);
    });
}

function getKodePotUmk(uangmuka) {
    var idskpd = $('#idskpd').val();
    var idkontrak = $('#idKontrak').val();

    $.getJSON(getbasepath() + "/spmblls/json/getKodePotUmk", {idskpd: idskpd, idkontrak: idkontrak},
    function(result) {
        var kodepot = result.aData['kodePotUmk'];
        // KODE POT UMK DI TMDPAKEGIATAN, BUAT TAU DIA MULTIYEAR / GA

        if (kodepot == "1") {
            if (uangmuka == "1") {
                document.getElementById("potonganUangMuka").disabled = true;
            } else {
                document.getElementById("potonganUangMuka").disabled = false;
            }
        } else {
            document.getElementById("potonganUangMuka").disabled = true;
        }

    });
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function getDataBankDki() {
    var kodebank = $('#kodeBankTransfer').val();
    var norek = $('#noRekeningPFK').val().replace(/\D/g, '');

    var panjangnorek = norek.length;
    var kodeva = $('#kodeva').val();

    //console.log("virtaBank == " + kodeva);
    //console.log("kodeva == " + $('#kodeva').val());

    $('#dkinamabank').val("");
    $('#dkikodebank').val("");
    $('#dkinorek').val("");
    $('#dkialamat').val("");
    $('#dkinama').val("");
    //$('#dkinpwp').val("");


    if ((norek !== "") && (document.getElementById("virtaBank").checked == false)) {
        var varurl = getbasepath() + "/postdata/json/ceknorek";

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

                if (kodebank == '111') {
                    $('#dkinamabank').val(data.namabank);
                    $('#dkikodebank').val(data.kodebank);
                    $('#dkinorek').val(data.norek);
                    $('#dkialamat').val(data.alamat);
                    $('#dkinama').val(data.nama);
                    //$('#dkinpwp').val(data.npwp);
                    response76 = "00";
                    $('#namaRekananBank').val(data.nama);

                } else {
                    $('#dkinamabank').val(data["bank"]);
                    $('#dkikodebank').val(data["code"]);
                    $('#dkinorek').val(data["account"]);
                    $('#dkinama').val(data["name"].trim());
                    $('#dkialamat').val("");
                    //$('#dkinpwp').val("");
                    response76 = data["response"];
                    $('#namaRekananBank').val(data["name"].trim());
                }

                if (kodebank !== '111' && response76 == "76") {
                    bootbox.alert("Data Rekening Tidak Ditemukan");
                }

            },
            error: function(xhr) {
                console.error(xhr);
                bootbox.alert("Sambungan Bank DKI Terputus");
                //$('#btnSimpan').attr("disabled", true);
            }
        }).always(function(data) {

        });
    }

}

function getDataNPWP() {
    var varurl = getbasepath() + "/postdata/json/inquirynpwp";
    var dataac = [];
    var datajour = {
        npwp: $('#noNpwp').val()
    };
    dataac = datajour;
//
    $.ajax({
        url: varurl,
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(dataac),
        contentType: 'text/plain; charset=utf-8',
        headers: {
            'Accept': 'application/json, text/javascript',
            'Content-Type': 'application/json',
        },
        success: function(result) {

            if (result['error'] == null) {
                $("#namaNpwp").val(result['NAMA']);
                $("#alamatNpwp").val(result['ALAMAT']);
                $("#kotaNpwp").val(result['KABKOT']);
                $("#kodePkp").val(result['STATUS_PKP']);
            } else {
                bootbox.alert(result['error']);
            }
        },
        error: function() {
            bootbox.alert("Sambungan DJP Terputus");
        }
    });
}

function cekvalidasi() {

    var kodeva = $('#kodeva').val();
    var namatujuan;
    if ($("#kodeBankTransfer").val() == "111") {
        namatujuan = $("#namaRekananBank").val();
    } else {
        namatujuan = $("#namaRekananBank").val().substr(0, 30);
    }

    if (($('#noRekeningPFK').val() !== "") && (document.getElementById("virtaBank").checked == false)) {
        if ($("#noRekeningPFK").val() !== $("#dkinorek").val()) {
            bootbox.alert("Cek Koneksi ke Bank DKI");
            return false;

        } else if ($("#dkinama").val() == "GENERAL ERROR" || $("#dkinorek").val() == "GENERAL ERROR") {
            bootbox.alert("Cek Koneksi ke Bank DKI");
            return false;

        } else if ($("#dkinama").val() == "rekening Tidak Ada Pada Master/rekening Tidak Aktif") {
            bootbox.alert("Rekening Tidak Ada Pada Master/Rekening Tidak Aktif");
            return false;

        } else if (namatujuan !== $("#dkinama").val().trim()) {
            bootbox.alert("Nama Perusahaan (Bank) Harus Sama dengan Hasil Inquiry");
            return false;

        } else {
            return true;
        }
    } else {
        return true;
    }

}

function cekVA() {
    if (document.getElementById("virtaBank").checked == true) {
        $('#kodeva').val("1");
        $('#dkinamabank').val("");
        $('#dkikodebank').val("");
        $('#dkinorek').val("");
        $('#dkialamat').val("");
        $('#dkinama').val("");
        // $('#dkinpwp').val("");
    } else {
        $('#kodeva').val("0");
        getDataBankDki();
    }

}