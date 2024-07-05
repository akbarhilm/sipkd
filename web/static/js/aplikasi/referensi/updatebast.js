$(document).ready(function() {
    getKodeUMK();
    setcbUMK();


    $('#idKegAwal').val($('#idkegiatan').val());
});
var totalindex = 0;
var totalawal;
var sisabasttotal, kodeumk, kodeMY;

function ubahlink() {
    var idkegiatan = $('#idkegiatan').val();
    // $("#spprincitable :input[type='text']").attr("readonly", false);
    $("#popupakun").attr("href", getbasepath() + "/bast/listpopupakun/" + idkegiatan + "?target='_top");
}


function gridakun() {

    var urljson = getbasepath() + "/bast/json/listakunkontrakrinci";
    $("#akunpopup").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#akunpopup').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "iDisplayLength": 50,
            "bInfo": true,
            "bScrollCollapse": true,
            "bAutoWidth": false,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idkegiatan", value: $('#idkegiatan').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "idbast", value: 0000},
                {name: "idkontrak", value: $('#idKontrak').val()},
                {name: "nobast", value: $('#noBast').val()},
                {name: "tahun", value: $('#tahunAnggaran').val()},
                {name: "kodemulti", value: kodeMY}
                );
            }, "fnDrawCallback": function() {
                formatnumberonkeyup();
                $(".inputmoney2").autoNumeric('init', {aSep: '.', aDec: ','});
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

                var isceked = aData['nilaiBast'] > 0 ? 'checked' : '';
                var readonly = 'readonly';
                if (isceked === 'checked') {
                    readonly = '';
                }

                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";
                //var tesnilai = 300000000000;
                totalindex = totalindex + 1;

                var namaAkun = "<input type='hidden' id='namaAkun" + index + "' value='" + index + "' />";
                var idAkun = "<input type='hidden' id='idAkun" + index + "'  name='idAkun" + index + "' value='" + aData['akun']['idAkun'] + "' />";
                var prestasi = "<input type='text'   id='nilaiprestasi" + index + "'    name='nilaiprestasi" + index + "'   value='" + aData['nilaiPrestasi'] + "'  class='inputmoney sppnilai'      />";
                var bast = "<input type='text'   id='nilaibast" + index + "'    name='nilaibast" + index + "'   value='" + aData['nilaiBast'] + "'   class='inputmoney sppnilai'  " + readonly + "  onkeyup=pasangvalidasi(" + index + ");hitungtotalbast() />"; //pasangvalidatebesarperfield(" + index + "),cekTotal(" + index + ")

                var nilaianggaran = "<input type='hidden'  class='inputmoney'  id='nilaianggaran" + index + "'  name='nilaianggaran" + index + "'  value='" + aData['sisaSpd'] + "'       />";
                var idBl = "<input type='hidden' id='idBl" + index + "'  name='idBl" + index + "' value='" + aData['idBl'] + "' />";
                var idspd = "<input type='hidden' id='idspd" + index + "'  name='idspd" + index + "' value='" + aData['idSpd'] + "' />";
                var idbast = "<input type='hidden' id='idbast" + index + "'  name='idbast" + index + "' value='" + aData['idBast'] + "' />";
                var namaakuntext = "<textarea readonly  style='border:none;margin:0;width:99%;'>" + aData['akun']['namaAkun'] + "</textarea>"

                var addoreditval;

                if (aData['nilaiBast'] > 0 || aData['idBast'] !== 0) {
                    addoreditval = "edit";
                } else {
                    addoreditval = "add";
                }

                var addoredit = "<input type='hidden'  id='addoredit" + index + "'  name='addoredit" + index + "'  value='" + addoreditval + "'  />";

                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(namaakuntext + idAkun + namaAkun);
                $('td:eq(3)', nRow).html(accounting.formatNumber(aData['nilaiSpd']));
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['sisaSpd']));
                $('td:eq(5)', nRow).html(prestasi);
                $('td:eq(6)', nRow).html(bast + idBl + nilaianggaran + idspd);
                $('td:eq(7)', nRow).html(inputcek + addoredit + idbast);


                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpd", "bSortable": false, sClass: "center", sWidth: '3%'},
                {"mDataProp": "noSpd", "bSortable": true, sClass: "center", sWidth: '8%'},
                {"mDataProp": "akun.namaAkun", "bSortable": false, sClass: "right", sWidth: '35%'},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "right", sWidth: '15%'},
                {"mDataProp": "sisaSpd", "bSortable": false, sClass: "right", sWidth: '15%'},
                {"mDataProp": "nilaiPrestasi", "bSortable": false, sClass: "right", sWidth: '11%'},
                {"mDataProp": "nilaiBast", "bSortable": false, sClass: "-", sWidth: '15%'},
                {"mDataProp": "idSpd", "bSortable": false, sClass: "-", sWidth: '3%'}
            ]

        });
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

    getbanyakakun();
}

function getbanyakakun() {
    $.getJSON(getbasepath() + "/bast/json/banyakpopupakun", {"idkegiatan": $('#idkegiatan').val(), "idspd": $('#idSpd').val(), "idbast": 0
        , "idskpd": $('#idskpd').val(), "tahun": $('#tahunAnggaran').val(), "idkontrak": $('#idKontrak').val(), "nobast": 0},
    function(result) {
        $('#banyakakun').val(result);
    });
}

function getsisabast() {
    var idbast = $('#idBast').val();
    var noBast = $('#noBast').val();

    if (idbast == "") {
        idbast = 0;
    }
    $.getJSON(getbasepath() + "/bast/json/getsisabast", {"idkegiatan": $('#idkegiatan').val(), "idbast": idbast
        , "idskpd": $('#idskpd').val(), "idkontrak": $('#idKontrak').val(), "nobast": $('#noBast').val(), "kodemulti": kodeMY},
    function(result) {

        var nilaikontrak = result.aData[0]['nilaiKontrak'];
        var bastsebelum = result.aData[0]['bastSebelum'];
        var sisabast = result.aData[0]['sisaBast'];
        var totalbast = result.aData[0]['nilaiBastEntry'];

        console.log("result nilaiBastEntry = " + totalbast);

        $("#nilaiKontrak").val(accounting.formatNumber(nilaikontrak));
        $("#bastSebelum").val(bastsebelum);
        $("#sisaBast").val(accounting.formatNumber(sisabast));
        $("#totalbast").val(accounting.formatNumber(totalbast));

        sisabasttotal = sisabast;

        getKontrakRinci(nilaikontrak);
    });
}

function setsisabastplustotal() {
    var sisabast = parseFloat(accounting.unformat($('#sisaBast').val(), ","));
    var totawal = parseFloat(accounting.unformat($('#totalbast').val(), ","));

    //bootbox.alert("Sisa BAST = "+sisabast+" || total awal = "+totawal);
    var sisa = sisabast + totawal;
    //$('#sisaBast').val(accounting.formatNumber(sisa));

}

function getidspd() {

    $.getJSON(getbasepath() + "/bast/json/getidspd", {"idkontrak": $('#idKontrak').val()},
    function(result) {

        idspdget = result;
        getKodeUMK();


    });
}

function cekTotal(index) {
    var i;
    var total = 0;

    for (i = 1; i <= totalindex; i++) {
        total = total + parseFloat(accounting.unformat($('#nilaibast' + i).val(), ","));
    }

    $('#totalbast').val(accounting.formatNumber(total));

    var totalbast = parseFloat(accounting.unformat($('#totalbast').val(), ","));
    var nilaisisa = parseFloat(accounting.unformat($('#sisaBast').val(), ","));

    var sisaupdate = nilaisisa + totalawal;
    if (sisaupdate < totalbast) {
        bootbox.alert("Total BAST tidak boleh lebih besar dari nilai sisa BAST");
        $('#nilaibast' + index).val(0);
        cekTotal(index);
    }
}

function validasinilaibast(index) {
    // untuk menghitung total bast
    var i;
    var total = 0;

    for (i = 1; i <= totalindex; i++) {
        total = total + parseFloat(accounting.unformat($('#nilaibast' + i).val(), ","));
    }

    $('#totalbast').val(accounting.formatNumber(total));

    var totalbast = parseFloat(accounting.unformat($('#totalbast').val(), ","));
    var nilaisisa = parseFloat(accounting.unformat($('#sisaBast').val(), ","));

    var sisaupdate = nilaisisa + totalawal;
    var sisaspd = parseFloat(accounting.unformat($('#nilaianggaran' + index).val(), ","));
    var nilaivalidasi, pesan;

    if (sisaupdate < sisaspd) {
        nilaivalidasi = sisaupdate;
        pesan = "Total BAST tidak boleh lebih besar dari nilai sisa BAST";
    }

    if (sisaupdate > sisaspd) {
        nilaivalidasi = sisaspd;
        pesan = "Total BAST tidak boleh lebih besar dari nilai sisa SPD";
    }

    if (nilaivalidasi < totalbast) {
        bootbox.alert("" + pesan);
        $('#nilaibast' + index).val(accounting.formatNumber(nilaivalidasi));
        // cekTotal(index);
        total = 0;
        for (i = 1; i <= totalindex; i++) {
            total = total + parseFloat(accounting.unformat($('#nilaibast' + i).val(), ","));
        }

        $('#totalbast').val(accounting.formatNumber(total));
    }

}

function pasangvalidatebesarperfield(idakun) {

    var nilaiSpd = parseFloat(accounting.unformat($('#nilaibast' + idakun).val(), ","));//parseFloat($("#nilaibast" + idakun).autoNumeric('get'));
    var nilaiangg = parseFloat(accounting.unformat($('#nilaianggaran' + idakun).val(), ","));//parseFloat($("#nilaianggaran" + idakun).autoNumeric('get'));
    var status = nilaiSpd > nilaiangg ? false : true;
    // console.log(" nilaiSpd " + nilaiSpd + "  nilaiangg " + nilaiangg)
    if (!status) {
        $("#nilaibast" + idakun).prop('readonly', true);

        bootbox.alert("Nilai BAST tidak boleh lebih besar dari nilai sisa SPD.", function() {
            $("#nilaibast" + idakun).autoNumeric('set', nilaiangg);
            $("#nilaibast" + idakun).focus();
            $("#nilaibast" + idakun).prop('readonly', false);
            cekTotal(idakun);
            //return false;
        });

    } else {
        $("#nilaibast" + idakun).val($("#nilaibast" + idakun).val());
        return true;
    }

    cekTotal(idakun);
}

function hitungtotal() {
    var i;
    var total = 0;

    for (i = 1; i <= totalindex; i++) {
        total = total + parseFloat(accounting.unformat($('#nilaibast' + i).val(), ","));
    }

    $('#totalbast').val(accounting.formatNumber(total));
}

function hitungtotalExceptMe(myIndex) {
    var total = 0;
    var i;
    /*
     $(".sppnilai").each(function (index, item) {
     console.log("index" +index);
     if((myIndex-1) !== index){
     console.log("masuk exemyindex - 1 " +myIndex-1);
     total += parseFloat($(item).autoNumeric('get'));
     //bootbox.alert("My Index = "+myIndex+" || current index = "+index);
     }
     console.log("myindex - 1 " +myIndex-1);
     console.log("total === " + total);
     });
     */
    console.log("totalindex === " + totalindex);

    for (i = 1; i <= totalindex; i++) {
        if ((myIndex) !== i) {
            total = total + parseFloat(accounting.unformat($('#nilaibast' + i).val(), ","));
        }
    }
    //console.log("total hitungtotalExceptMe === " + total);
    totalSisaPaguMe = total;

}

function enablerow(obj, idbl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    } else {
        $("#nilaibast" + idbl).autoNumeric('set', 0);
    }
    setdisabled(param, idbl);
}
function setdisabled(param, idbl) {
    $("#nilaibast" + idbl).attr("readonly", param);
    /*
     if (param == false) {
     var nilaispj = accounting.unformat($("#nilaispj" + idbl).val(), ",");
     var sisaspj = accounting.unformat($("#sisaspj" + idbl).val(), ",");
     var nilaiisian = accounting.formatNumber((nilaispj == 0 ? sisaspj : nilaispj), 2, '.', ",")
     $("#nilaispj" + idbl).val(nilaiisian);
     }
     */
    hitungtotalbast();
}

function pasangvalidasi(idbl) {
    var idbas = $("#idAkun" + idbl).val();
    var nilaibast = accounting.unformat($("#nilaibast" + idbl).val(), ",");//$("#nilaibast" + idbl).autoNumeric('get');
    var nilaiSisaBast = accounting.unformat($("#sisaBast").val(), ",");
    var nilaiSisaSpd = accounting.unformat($("#nilaianggaran" + idbl).val(), ",");
    var sisaumk = accounting.unformat($("#sisaumk" + idbas).val(), ",");
    var sisakontrak = accounting.unformat($("#sisakontrak" + idbas).val(), ",");

    var status;
    // console.log((nilaispp <= totalAngaran) + "  " + status + "  nilaispp = " + nilaispp + " nilaispd =  " + nilaispd + " totalAngaran = " + totalAngaran + " nilaiSisaPagu " + nilaiSisaPagu);
    var nilaivalidasi;
    var pesan = "";

    var i;
    var totalakun = 0;
    var sisaakun = 0;

    // get total nilai input untuk akun yg sedang diinput
    for (i = 1; i <= totalindex; i++) {
        if ($("#idAkun" + i).val() == idbas) {
            totalakun = totalakun + parseFloat(accounting.unformat($('#nilaibast' + i).val(), ","));
        }
    }

    if (document.getElementById("cbUMK").checked == true) {
        sisaakun = sisaumk;
    } else {
        sisaakun = sisakontrak;
    }

    if (Number(sisaakun) < Number(nilaiSisaSpd)) {
        nilaivalidasi = sisaakun;
        pesan = "Total Akun BAST tidak boleh lebih besar dari Sisa Akun";
    } else {
        nilaivalidasi = nilaiSisaSpd;
        pesan = "Nilai BAST tidak boleh lebih besar dari nilai Sisa SPD";
    }

    if (totalakun > sisaakun) {
        bootbox.alert(pesan, function() {
            //$("#nilaibast" + idbl).val(accounting.formatNumber(nilaivalidasi, 2, '.', ","));
            $("#nilaibast" + idbl).autoNumeric('set', 0);
            $("#nilaibast" + idbl).focus();
            hitungtotalbast();
        });
    }

    if (nilaibast > nilaivalidasi) {
        bootbox.alert(pesan, function() {
            //$("#nilaibast" + idbl).val(accounting.formatNumber(nilaivalidasi, 2, '.', ","));
            $("#nilaibast" + idbl).autoNumeric('set', 0);
            $("#nilaibast" + idbl).focus();
            hitungtotalbast();
        });
    }

}

function hitungtotalbast() {
    var i;
    var total = 0;
    // EDIT 25 jan 2016 by zainab, untuk menghitung by checkbox
    /*
     for (i = 1; i <= totalindex; i++) {
     total = total+ parseFloat(accounting.unformat($('#nilaibast' + i).val(), ","));
     }

     $('#totalbast').val(accounting.formatNumber(total));
     */

    var searchIDs = $("#akunpopup input:checkbox:checked").not("#pilihall").map(function() {
        return $(this).val();
    }).get();

    $.each(searchIDs, function(idx, data) {
        total += parseFloat(accounting.unformat($("#nilaibast" + data).val(), ","));
    })
    $('#totalbast').val(accounting.formatNumber(total));
}

function setUMK() {

    if (document.getElementById("cbUMK").checked == true) {
        $('#statusUM').val(1);
        // console.log("checked true");
    } else {
        $('#statusUM').val(0);
        //console.log("checked false");
    }

}

function setcbUMK() {
    console.log("statusUangMuka val = " + $('#statusUangMuka').val());
    if ($('#statusUangMuka').val() == 1) {
        document.getElementById("cbUMK").checked = true;
        $('#statusUM').val(1);
    } else {
        document.getElementById("cbUMK").checked = false;
        $('#statusUM').val(0);
    }
}

function getKontrakRinci(nilaikontrak) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkontrak = $('#idKontrak').val();

    $.getJSON(getbasepath() + "/bast/json/getKontrakRinci", {tahun: tahun, idskpd: idskpd, idkontrak: idkontrak},
    function(result) {

        var nilaikontrakrinci = result.aData[0]['nilaiKontrak'];
        var kontrakrinci = result.aData[0]['banyakKontrakRinci'];
        var saldoumk = result.aData[0]['saldoUMK'];

        if (kodeMY == "1") { // JIKA MULTIYEAR
            nilaikontrakrinci = nilaikontrakrinci + saldoumk;
        }

        if (kontrakrinci == 0 || nilaikontrakrinci < nilaikontrak) {
            bootbox.alert("Silahkan Input Kontrak Rinci !");
        } else {

        }

    });
}

function getKodeUMK() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkegiatan = $('#idkegiatan').val();

    $.getJSON(getbasepath() + "/bast/json/getKodeUMK", {"idkegiatan": idkegiatan, "idskpd": idskpd, "tahun": tahun},
    function(result) {

        kodeumk = result.aData['kodeUMK'];
        var kodepot = result.aData['kodePotUMK'];

        if (kodeumk == "0" && kodepot == "1") { // MULTIYEAR
            kodeMY = "1";
        } else { // BUKAN MULTIYEAR
            kodeMY = "0";
        }

        if (kodeumk == "1") {
            //console.log("masuk if = "+kodeumk);
            document.getElementById("labelUMK").style.display = "block";
        } else {
            //console.log("masuk else = "+kodeumk);
            document.getElementById("labelUMK").style.display = "none";
            $('#statusUM').val(0);
        }

        getsisabast();
        gridakun();
        gridpagukontrak();

    });
}

function ceknilaientry() {
    var searchIDs = $("#akunpopup input:checkbox:checked").not("#pilihall").map(function() {
        return $(this).val();
    }).get();

    $.each(searchIDs, function(idx, data) {
        pasangvalidasi(data);
    });
}


function gridpagukontrak() {
    var urljson = getbasepath() + "/bast/json/listpaguakunkontrak";
    $("#gridpagu").show();
    if (typeof myTablePagu == 'undefined') {
        myTablePagu = $('#gridpagu').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            //"iDisplayLength": 50,
            "bInfo": true,
            "bScrollCollapse": true,
            "bAutoWidth": false,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idkegiatan", value: $('#idkegiatan').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                {name: "idkontrak", value: $('#idKontrak').val()},
                {name: "nobast", value: $('#noBast').val()},
                {name: "tahun", value: $('#tahunAnggaran').val()}
                );
            }, "fnDrawCallback": function() {
                formatnumberonkeyup();
                $(".inputmoney2").autoNumeric('init', {aSep: '.', aDec: ','});
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

                var idbas = aData['akun']['idAkun'];
                var sisakontrak = "<input type='hidden' id='sisakontrak" + idbas + "'  name='sisakontrak" + idbas + "'  value='" + aData['sisaKontrak'] + "'       />";
                var sisaumk = "<input type='hidden' id='sisaumk" + idbas + "'  name='sisaumk" + idbas + "'  value='" + aData['sisaUMK'] + "' />";
                var idakun = "<input type='hidden' id='idbas" + idbas + "'  name='idbas" + idbas + "'  value='" + aData['akun']['idAkun'] + "' />";
                var namaakuntext = "<textarea readonly  style='border:none;margin:0;width:99%;'>" + aData['akun']['namaAkun'] + "</textarea>"

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(namaakuntext + idakun + sisaumk + sisakontrak);
                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['nilaiAnggaran']));
                $('td:eq(3)', nRow).html(accounting.formatNumber(aData['sisaKontrak']));
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['saldoUMK']));
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['sisaUMK']));

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun.idAkun", "bSortable": false, sClass: "center", sWidth: '5%'},
                {"mDataProp": "akun.namaAkun", "bSortable": false, sClass: "right", sWidth: '35%'},
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right", sWidth: '15%'},
                {"mDataProp": "sisaKontrak", "bSortable": false, sClass: "right", sWidth: '15%'},
                {"mDataProp": "saldoUMK", "bSortable": false, sClass: "right", sWidth: '15%'},
                {"mDataProp": "sisaUMK", "bSortable": false, sClass: "right", sWidth: '15%'}

            ]

        });

    }
    else
    {
        myTablePagu.fnClearTable(0);
        myTablePagu.fnDraw();
    }
}