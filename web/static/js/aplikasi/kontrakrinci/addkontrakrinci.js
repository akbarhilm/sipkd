$(document).ready(function() {
    //$("#spdBTLMasterform").valid();
    gridakun();

});

var totalindex = 0;
var totalSisaPaguMe = 0;
var statusSimpan = 0;
var totalkontrakgrid = 0;

function simpan() {
    var totalkontrak = parseFloat(accounting.unformat($('#totalkontrak').val(), ","));
    var nilaikontrak = parseFloat(accounting.unformat($('#nilaiKontrak').val(), ","));

    var nilaiumk, nilaik;
    var i;
    var datalengkap = true;

    for (i = 0; i < totalindex; i++) {
        nilaiumk = parseFloat(accounting.unformat($('#nilaiumk' + i).val(), ","));
        nilaik = parseFloat(accounting.unformat($('#nilaikontrak' + i).val(), ","));

        if (nilaik < nilaiumk) {
            datalengkap = false;
        }
    }

    //console.log("totalindex = " + totalindex);
    //console.log("nilaik = " + nilaik);
    //console.log("nilaiumk = " + nilaiumk);

    if (totalkontrak !== nilaikontrak) {
        bootbox.alert("Total Kontrak Rinci Harus Sama Dengan Nilai Kontrak");
    } else if (datalengkap == false) {
        bootbox.alert("Nilai UMK Tidak Boleh Lebih Besar dari Nilai Kontrak");
    } else {
        if (statusSimpan > 0) {
            updateRinci();
        } else {
            simpanRinci();
        }
    }

}

function simpanRinci() {

    var varurl = getbasepath() + "/kontrakrinci/json/prosessimpan";
    var dataac = [];
    var nilailist = [];
    var i;

    for (i = 0; i < totalindex; i++) { // list
        var id = i + 1;

        var pararr = {
            idbas: $("#idBas" + id).val(),
            penanda: $("#penanda" + id).val(),
            nilairinci: $("#nilaikontrak" + id).val(),
            nilaiumk: $("#nilaiumk" + id).val()
        };
        nilailist[i] = pararr;
    }

    var datajour = {
        tahun: $('#tahun').val(),
        idskpd: $('#idskpd').val(),
        idkegiatan: $('#idKegiatan').val(),
        idkontrak: $("#idKontrak").val(),
        nilailist: nilailist

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

        bootbox.alert("Data Kontrak Rinci Berhasil Disimpan");
    });

}

function updateRinci() {

    var varurl = getbasepath() + "/kontrakrinci/json/prosesupdate";
    var dataac = [];
    var nilailist = [];
    var i;

    for (i = 0; i < totalindex; i++) { // list
        var id = i + 1;

        var pararr = {
            idbas: $("#idBas" + id).val(),
            idrinci: $("#idRinci" + id).val(),
            penanda: $("#penanda" + id).val(),
            nilairinci: $("#nilaikontrak" + id).val(),
            nilaiumk: $("#nilaiumk" + id).val()
        };
        nilailist[i] = pararr;
    }

    var datajour = {
        tahun: $('#tahun').val(),
        idskpd: $('#idskpd').val(),
        idkegiatan: $('#idKegiatan').val(),
        idkontrak: $("#idKontrak").val(),
        nilailist: nilailist

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

        bootbox.alert("Data Kontrak Rinci Berhasil Disimpan");
    });

}

function gridakun() {

    var urljson = getbasepath() + "/kontrakrinci/json/listakun";
    $("#akunpopup").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#akunpopup').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "iDisplayLength": 100,
            "bInfo": true,
            "bScrollCollapse": true,
            "bAutoWidth": false,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idkegiatan", value: $('#idKegiatan').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                //{name: "idspd", value: $('#idSpd').val()},
                {name: "idkontrak", value: $('#idKontrak').val()},
                {name: "tahun", value: $('#tahun').val()}
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
                var penandaval; // = aData['nilaiKontrak'] > 0 ? 1 : 0;

                var isceked = aData['nilaiKontrak'] > 0 ? 'checked' : '';
                var iscekedumk = aData['nilaiUmk'] > 0 ? 'checked' : '';

                var readonly = 'readonly';
                if (isceked === 'checked' || iscekedumk === 'checked') {
                    readonly = '';
                    penandaval = 1;
                } else {
                    penandaval = 0;
                }

                totalindex = totalindex + 1;
                statusSimpan = statusSimpan + aData['idKontrakRinci']; // untuk cek add / edit
                //console.log("statusSimpan = "+statusSimpan);
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";
                var idBas = "<input type='hidden' id='idBas" + index + "'  name='idBas" + index + "' value='" + aData['idBas'] + "' />";
                var namaakuntext = "<textarea readonly  style='border:none;margin:0;width:300px;' >" + aData['namaAkun'] + "</textarea>";
                var idRinci = "<input type='hidden' id='idRinci" + index + "'  name='idRinci" + index + "' value='" + aData['idKontrakRinci'] + "' />";
                var nilaikontrak = "<input type='text' id='nilaikontrak" + index + "' name='nilaikontrak" + index + "'   value='" + aData['nilaiKontrak'] + "'  class='inputmoney sppnilai'  " + readonly + "  onkeyup=pasangvalidasi(" + index + ");hitungtotalkontrak() onchange='cekbast(" + index + ")' />";
                var nilaiumk = "<input type='text' id='nilaiumk" + index + "' name='nilaiumk" + index + "'   value='" + aData['nilaiUmk'] + "'  class='inputmoney sppnilai'  " + readonly + "  onkeyup=pasangvalidasiumk(" + index + ") />";
                var nilaisisaumk = "<input type='hidden'  class='inputmoney'  id='nilaisisaumk" + index + "'  name='nilaisisaumk" + index + "'  value='" + aData['saldoUMK'] + "'  />";
                var nilaisisa = "<input type='hidden'  class='inputmoney'  id='nilaisisa" + index + "'  name='nilaisisa" + index + "'  value='" + aData['sisaKontrak'] + "'       />";
                // var nilaibast = "<input type='hidden'  class='inputmoney'  id='nilaibast" + index + "'  name='nilaibast" + index + "'  value='" + aData['nilaiBast'] + "'  />";
                var penanda = "<input type='hidden' id='penanda" + index + "' value='" + penandaval + "' />";
                var addoreditval;

                if (aData['idKontrakRinci'] !== 0) {
                    addoreditval = "edit";
                } else {
                    addoreditval = "add";
                }

                totalkontrakgrid = totalkontrakgrid + aData['nilaiKontrak'];
                $('#totalkontrak').val(accounting.formatNumber(totalkontrakgrid, 2, '.', ","));

                var addoredit = "<input type='hidden'  id='addoredit" + index + "'  name='addoredit" + index + "'  value='" + addoreditval + "'  />";

                $('td:eq(0)', nRow).html(index + idRinci);
                $('td:eq(1)', nRow).html(namaakuntext + idBas + nilaisisa);
                console.log("nilai angg = "+aData['nilaiAngg']);
                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['nilaiAngg']));
                $('td:eq(3)', nRow).html(accounting.formatNumber(aData['saldoUMK']));
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['kontrakSebelum']));
                $('td:eq(5)', nRow).html(accounting.formatNumber(aData['sisaKontrak']));
                $('td:eq(6)', nRow).html(nilaikontrak);
                $('td:eq(7)', nRow).html(nilaiumk + nilaisisaumk);
                //$('td:eq(8)', nRow).html(accounting.formatNumber(aData['nilaiBast']));
                $('td:eq(8)', nRow).html(inputcek + penanda + addoredit);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idKontrakRinci", "bSortable": false, sClass: "center", sWidth: '2%'},
                {"mDataProp": "namaAkun", "bSortable": false, sClass: "", sWidth: '20%'},
                {"mDataProp": "saldoUMK", "bSortable": false, sClass: "right", sWidth: '11%'},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "right", sWidth: '11%'},
                {"mDataProp": "kontrakSebelum", "bSortable": false, sClass: "right", sWidth: '11%'},
                {"mDataProp": "sisaKontrak", "bSortable": false, sClass: "right", sWidth: '11%'},
                {"mDataProp": "nilaiKontrak", "bSortable": false, sClass: "right", sWidth: '11%'},
                {"mDataProp": "nilaiUmk", "bSortable": false, sClass: "right", sWidth: '11%'},
                //{"mDataProp": "nilaiBast", "bSortable": false, sClass: "right", sWidth: '11%'},
                {"mDataProp": "idKontrakRinci", "bSortable": false, sClass: "center", sWidth: '2%'}

            ]

        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function getbanyakakun() {
    $.getJSON(getbasepath() + "/bast/json/banyakpopupakun", {"idkegiatan": $('#idkegiatan').val(), "idspd": $('#idSpd').val(), "idbast": 0
        , "idskpd": $('#idskpd').val(), "tahun": $('#tahunAnggaran').val(), "idkontrak": $('#idKontrak').val(), "nobast": 0},
    function(result) {
        $('#banyakakun').val(result);
    });
}


function pasangvalidatebesarperfield(idakun) {

    var nilaiSpd = parseFloat(accounting.unformat($('#nilaikontrak' + idakun).val(), ","));//parseFloat($("#nilaibast" + idakun).autoNumeric('get'));
    var nilaiangg = parseFloat(accounting.unformat($('#nilaisisa' + idakun).val(), ","));//parseFloat($("#nilaianggaran" + idakun).autoNumeric('get'));
    var status = nilaiSpd > nilaiangg ? false : true;
    // console.log(" nilaiSpd " + nilaiSpd + "  nilaiangg " + nilaiangg)
    if (!status) {
        $("#nilaikontrak" + idakun).prop('readonly', true);

        bootbox.alert("Nilai kontrak rinci tidak boleh lebih besar dari nilai sisa kontrak.", function() {
            $("#nilaikontrak" + idakun).autoNumeric('set', nilaiangg);
            $("#nilaikontrak" + idakun).focus();
            $("#nilaikontrak" + idakun).prop('readonly', false);
            cekTotal(idakun);
            //return false;
        });

    } else {
        $("#nilaikontrak" + idakun).val($("#nilaikontrak" + idakun).val());
        return true;
    }

    cekTotal(idakun);

}


function cekTotal(index) {
    var i;
    var total = 0;

    for (i = 1; i <= totalindex; i++) {
        total = total + parseFloat(accounting.unformat($('#nilaikontrak' + i).val(), ","));
    }

    $('#totalkontrak').val(accounting.formatNumber(total));

    var totalkontrak = parseFloat(accounting.unformat($('#totalkontrak').val(), ","));
    var nilaikontrak = parseFloat(accounting.unformat($('#nilaiKontrak').val(), ","));

    if (nilaikontrak < totalkontrak) {
        bootbox.alert("Total kontrak rinci tidak boleh lebih besar dari nilai kontrak");
        $('#nilaikontrak' + index).val(0);
        cekTotal(index);
    }
}

function hitungtotalkontrak() {
    var i;
    var total = 0;
    // EDIT 25 jan 2016 by zainab, untuk menghitung by checkbox
    /*for (i = 1; i <= totalindex; i++) {
     total = total+ parseFloat(accounting.unformat($('#nilaibast' + i).val(), ","));
     }
     $('#totalbast').val(accounting.formatNumber(total));
     */
    var searchIDs = $("#akunpopup input:checkbox:checked").not("#pilihall").map(function() {
        return $(this).val();
    }).get();

    $.each(searchIDs, function(idx, data) {
        //console.log("nilaikontrak = "+accounting.unformat($("#nilaikontrak" + data).val() ,","));
        total += parseFloat(accounting.unformat($("#nilaikontrak" + data).val(), ","));
    })
    //console.log("total = "+total);
    $('#totalkontrak').val(accounting.formatNumber(total, 2, '.', ","));  // agar nilai dibelakang komanya terbaca
}

function validasinilaibast(index) {
    hitungtotalkontrak();

    var totalbast = parseFloat(accounting.unformat($('#totalkontrak').val(), ","));
    var nilaisisa = parseFloat(accounting.unformat($('#nilaiKontrak').val(), ","));

    //var sisaupdate = nilaisisa;
    var sisaspd = parseFloat(accounting.unformat($('#nilaisisa' + index).val(), ","));
    var nilaivalidasi, pesan;

    if (nilaisisa < sisaspd) {
        nilaivalidasi = nilaisisa;
        pesan = "Total kontrak rinci tidak boleh lebih besar dari nilai sisa kontrak";
    }

    if (nilaisisa > sisaspd) {
        nilaivalidasi = sisaspd;
        pesan = "Total kontrak rinci tidak boleh lebih besar dari nilai sisa SPD";
    }

    if (nilaivalidasi < totalbast) {
        bootbox.alert("" + pesan);
        $('#nilaikontrak' + index).val(accounting.formatNumber(nilaivalidasi));

        hitungtotalkontrak();
    }

}

function hitungtotalExceptMe(myIndex) {
    var total = 0;
    var i;

    for (i = 1; i <= totalindex; i++) {
        if ((myIndex) !== i) {
            total = total + parseFloat(accounting.unformat($('#nilaikontrak' + i).val(), ","));
        }
    }
    //console.log("total hitungtotalExceptMe === " + total);
    totalSisaPaguMe = total;

}

function enablerow(obj, idbl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
        $("#penanda" + idbl).val(1);
    } else {
        $("#penanda" + idbl).val(0);
        $("#nilaikontrak" + idbl).autoNumeric('set', 0);
        $("#nilaiumk" + idbl).autoNumeric('set', 0);
    }
    setdisabled(param, idbl);
}
function setdisabled(param, idbl) {
    $("#nilaikontrak" + idbl).attr("readonly", param);
    $("#nilaiumk" + idbl).attr("readonly", param);

    /*
     if (param == false) {
     var nilaispj = accounting.unformat($("#nilaispj" + idbl).val(), ",");
     var sisaspj = accounting.unformat($("#sisaspj" + idbl).val(), ",");
     var nilaiisian = accounting.formatNumber((nilaispj == 0 ? sisaspj : nilaispj), 2, '.', ",")
     $("#nilaispj" + idbl).val(nilaiisian);
     }
     */
    hitungtotalkontrak();
}


function pasangvalidasi(idbl) {
    var nilaibast = accounting.unformat($("#nilaikontrak" + idbl).val(), ",");
    var nilaiSisaBast = accounting.unformat($("#nilaiKontrak").val(), ",");
    var nilaiSisaSPD = accounting.unformat($("#nilaisisa" + idbl).val(), ",");
    var status;
    // console.log((nilaispp <= totalAngaran) + "  " + status + "  nilaispp = " + nilaispp + " nilaispd =  " + nilaispd + " totalAngaran = " + totalAngaran + " nilaiSisaPagu " + nilaiSisaPagu);
    var nilaivalidasi;
    var pesan = "";

    hitungtotalExceptMe(idbl);
    var totalNilaiSisaBAST = nilaiSisaBast - totalSisaPaguMe;

    if (Number(nilaiSisaSPD) < Number(totalNilaiSisaBAST)) {
        nilaivalidasi = nilaiSisaSPD;
        pesan = "Nilai kontrak rinci tidak boleh lebih besar dari nilai sisa kontrak";
    } else {
        nilaivalidasi = totalNilaiSisaBAST;
        pesan = "Total kontrak rinci tidak boleh lebih besar dari nilai kontrak";
    }

    status = parseFloat(nilaibast) <= parseFloat(nilaivalidasi);

    if (!status) {
        bootbox.alert(pesan, function() {
            $("#nilaikontrak" + idbl).autoNumeric('set', 0);
            $("#nilaikontrak" + idbl).focus();
            hitungtotalkontrak();
        });

    } else {
        return true;
    }
}

function pasangvalidasiumk(idbl) {
    var nilaikontrak = accounting.unformat($("#nilaiKontrak").val(), ",");
    var nilaiumk = accounting.unformat($("#nilaiumk" + idbl).val(), ",");
    var sisaumk = accounting.unformat($("#nilaisisaumk" + idbl).val(), ",");
    var nilaiSisaSPD = accounting.unformat($("#nilaisisa" + idbl).val(), ",");
    var status;
    var nilaivalidasi;
    var ketsisa = "";

    console.log("nilai kontrak = " + Number(nilaiSisaSPD));
    console.log("nilai kontrak = " + Number(sisaumk));
    console.log("nilai kontrak = " + Number(nilaikontrak));
    console.log("min nilai = " + Math.min(Number(nilaiSisaSPD), Number(sisaumk), Number(nilaikontrak)));

    if (Number(nilaiSisaSPD) < Number(sisaumk)) {
        nilaivalidasi = nilaiSisaSPD;
        ketsisa = "Kontrak";
    } else {
        nilaivalidasi = sisaumk;
        ketsisa = "UMK";
    }

    //nilaivalidasi = Math.min(Number(nilaiSisaSPD), Number(sisaumk), Number(nilaikontrak));

    status = parseFloat(nilaiumk) <= parseFloat(nilaivalidasi);

    if (!status) {
        bootbox.alert("Nilai UMK Tidak Boleh Lebih Besar dai Sisa " + ketsisa, function() {
            $("#nilaiumk" + idbl).autoNumeric('set', nilaivalidasi);
            $("#nilaiumk" + idbl).focus();
        });

    } else {
        return true;
    }
}


function cekbast(id) {
    var nilaikontrak = accounting.unformat($("#nilaikontrak" + id).val(), ",");
    var nilaibast = accounting.unformat($("#nilaibast" + id).val(), ",");

    var status = parseFloat(nilaibast) <= parseFloat(nilaikontrak);
    var pesan = "Nilai kontrak tidak boleh lebih kecil dari nilai BAST";

    if (!status) {
        bootbox.alert(pesan, function() {
            $("#nilaikontrak" + id).autoNumeric('set', nilaibast);
            $("#nilaikontrak" + id).focus();
            hitungtotalkontrak();
        });

    } else {
        return true;
    }

}

function getNilaiKontrak() {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkontrak = $('#idKontrak').val();

    $.getJSON(getbasepath() + "/kontrakrinci/json/getNilaiKontrak", {tahun: tahun, idskpd: idskpd, idkontrak: idkontrak},
    function(result) {

        var nilai = accounting.formatNumber(result.aData['nilaiKontrak'], 2, '.', ",");

        $("#nilaiKontrak").val(nilai);

    });
}