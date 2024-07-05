$(document).ready(function() {
    getsisabast();
    getidspd();
    //gridakun();
    setcbUMK();
    

});
var totalindex = 0;
var totalawal;
var idspdget;
var sisabasttotal;

function ubahlink( ) {
    var idkegiatan = $('#idkegiatan').val();
    // $("#spprincitable :input[type='text']").attr("readonly", false);
    $("#popupakun").attr("href", getbasepath() + "/bast/listpopupakun/" + idkegiatan + "?target='_top");
}
function gridakun( ) {
    //console.log("hasill update");
    var urljson = getbasepath() + "/bast/json/listpopupakunupdate/" + $('#noBast').val();
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
                {name: "idspd", value: idspdget},
                {name: "idbast", value: $('#idBast').val()},
                {name: "idkontrak", value: $('#idKontrak').val()},
                {name: "tahun", value: $('#tahunAnggaran').val()}
                );
            }, "fnDrawCallback": function() {
                formatnumberonkeyup();

            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {
                var pageTotal = 0;
                if (aaData.length > 0) {
                    for (var i = iStart; i < iEnd; i++) {

                        pageTotal += parseFloat(aaData[i]['nilaiBast']);
                    }
                }
                //bootbox.alert("iStart == "+iStart+" -- iEnd == "+iEnd);
                $("#totalbast").val(accounting.formatNumber(pageTotal, 2, '.', ","));
                totalawal = pageTotal;
                setsisabastplustotal();

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
                totalindex = totalindex + 1;

                var isceked = aData['nilaiBast'] > 0 ? 'checked' : '';
                var readonly = 'readonly';
                if (isceked === 'checked') {
                    readonly = '';
                }

                var inputcek = "<input type='checkbox' class='cekpilih' value='" + index + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + index + ") " + isceked + " />";

                var namaAkun = "<input type='hidden' id='namaAkun" + index + "' value='" + index + "' />";
                var idAkun = "<input type='hidden' id='idAkun" + index + "'  name='idAkun" + index + "' value='" + aData['akun']['idAkun'] + "' />";
                var prestasi = "<input type='text'   id='nilaiprestasi" + index + "'    name='nilaiprestasi" + index + "'    value='" + aData['nilaiPrestasi'] + "'  class='inputmoney sppnilai'      />";

                var bast = "<input type='text'   id='nilaibast" + index + "'    name='nilaibast" + index + "'      class='inputmoney sppnilai'   " + readonly + "   value='" + aData['nilaiBast'] + "'  onkeyup= pasangvalidasi(" + index + ");hitungtotalbast()   />"; //pasangvalidatebesarperfield(" + index + "),cekTotal(" + index + ")
                var idkegiatan = "<input type='hidden'   id='idkegiatan" + index + "'    name='idkegiatan" + index + "'    value='" + aData['idkegiatan'] + "'          />";
                var idbast = "<input type='hidden'   name='idBast" + index + "' id='idBast" + index + "'  value='" + aData['idBast'] + "' />   ";
                var nilaianggaran = "<input type='hidden'  class='inputmoney'  id='nilaianggaran" + index + "'  name='nilaianggaran" + index + "'  value='" + aData['sisaSpd'] + "'       />";

                var namaakuntext = "<textarea readonly  style='border:none;margin:0;width:99%;'>" + aData['akun']['namaAkun'] + "</textarea>"
                var addoreditval;

                if (aData['nilaiBast'] > 0 || aData['idBast'] !== 0) {
                    addoreditval = "edit";
                } else {
                    addoreditval = "add";
                }

                var addoredit = "<input type='hidden'  id='addoredit" + index + "'  name='addoredit" + index + "'  value='" + addoreditval + "'  />";
                var idBl = "<input type='hidden' id='idBl" + index + "'  name='idBl" + index + "' value='" + aData['idBl'] + "' />";

                $('td:eq(1)', nRow).html(namaakuntext);
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['nilaiSpd']));
                $('td:eq(3)', nRow).html(accounting.formatNumber(aData['sisaSpd']));
                $('td:eq(4)', nRow).html(idAkun + namaAkun + prestasi + idkegiatan + idbast + nilaianggaran);
                $('td:eq(5)', nRow).html(bast + addoredit + idBl);
                $('td:eq(6)', nRow).html(inputcek);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun.idAkun", "bSortable": false, sClass: "center", sWidth: '5%'},
                {"mDataProp": "akun.namaAkun", "bSortable": true, sClass: "", sWidth: '43%'},
                {"mDataProp": "nilaiSpd", "bSortable": false, sClass: "right", sWidth: '10%'},
                {"mDataProp": "nilaiAnggaran", "bSortable": false, sClass: "right", sWidth: '10%'},
                {"mDataProp": "akun.idAkun", "bSortable": false, sClass: "-", sWidth: '7%'},
                {"mDataProp": "akun.idAkun", "bSortable": false, sClass: "-", sWidth: '10%'},
                {"mDataProp": "akun.idAkun", "bSortable": false, sClass: "-", sClass: "center", sWidth: '2%'}
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

    $.getJSON(getbasepath() + "/bast/json/banyakpopupakun", {"idkegiatan": $('#idkegiatan').val()
        , "idskpd": $('#idskpd').val(), "tahun": $('#tahunAnggaran').val(), "idspd": $('#idSpd').val()
        , "idbast": $('#idBast').val(), "idkontrak": $('#idKontrak').val(), "nobast": $('#noBast').val()},
    function(result) {
        $('#banyakakun').val(result);
        //console.log("getbanyakakun result -- "+result);
    });
}

function getsisabast() {
    var idbast = $('#idBast').val();
    var noBast = $('#noBast').val();

    if (idbast == "") {
        idbast = 0;
    }
    $.getJSON(getbasepath() + "/bast/json/getsisabast", {"idkegiatan": $('#idkegiatan').val(), "idbast": idbast
        , "idskpd": $('#idskpd').val(), "idkontrak": $('#idKontrak').val(), "nobast": $('#noBast').val()},
    function(result) {

        var nilaikontrak = result.aData[0]['nilaiKontrak'];
        var bastsebelum = result.aData[0]['bastSebelum'];
        var sisabast = result.aData[0]['sisaBast'];

        $("#nilaiKontrak").val(accounting.formatNumber(nilaikontrak));
        $("#bastSebelum").val(bastsebelum);
        $("#sisaBast").val(accounting.formatNumber(sisabast));
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
        gridakun();

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
    var nilaibast = accounting.unformat($("#nilaibast" + idbl).val(), ",");//$("#nilaibast" + idbl).autoNumeric('get');
    var nilaiSisaBast = accounting.unformat($("#sisaBast").val(), ",");
    var nilaiSisaSPD = accounting.unformat($("#nilaianggaran" + idbl).val(), ",");
    var status;
    // console.log((nilaispp <= totalAngaran) + "  " + status + "  nilaispp = " + nilaispp + " nilaispd =  " + nilaispd + " totalAngaran = " + totalAngaran + " nilaiSisaPagu " + nilaiSisaPagu);
    var nilaivalidasi;
    var pesan = "";

    hitungtotalExceptMe(idbl);
    //bootbox.alert("Total Nilai Sisa Pagu SPD == "+totalSisaPaguMe);
    var totalNilaiSisaBAST = nilaiSisaBast - totalSisaPaguMe;

    if (Number(nilaiSisaSPD) < Number(totalNilaiSisaBAST)) {
        nilaivalidasi = nilaiSisaSPD;
        pesan = "Nilai BAST tidak boleh lebih besar dari nilai Sisa SPD";
    } else {
        nilaivalidasi = totalNilaiSisaBAST;
        pesan = "Nilai BAST tidak boleh lebih besar dari nilai sisa BAST";
    }

    status = parseFloat(nilaibast) <= parseFloat(nilaivalidasi);

    if (!status) {
        bootbox.alert(pesan, function() {
            //$("#nilaibast" + idbl).val(accounting.formatNumber(nilaivalidasi, 2, '.', ","));
            $("#nilaibast" + idbl).autoNumeric('set', nilaivalidasi);
            $("#nilaibast" + idbl).focus();
            hitungtotalbast();
        });

    } else {
        return true;
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

function setUMK(){
    
    if (document.getElementById("cbUMK").checked == true){
        $('#statusUM').val(1);
       // console.log("checked true");
    } else {
        $('#statusUM').val(0);
        //console.log("checked false");
    }

}

function setcbUMK(){
    console.log("statusUangMuka val = " + $('#statusUangMuka').val());
     if ($('#statusUangMuka').val() == 1){
         document.getElementById("cbUMK").checked = true;
         $('#statusUM').val(1);
     } else{
         document.getElementById("cbUMK").checked = false;
         $('#statusUM').val(0);
     }
}

function getKontrakRinci(nilaikontrak) {
    var tahun = $('#tahun').val();
    var idskpd = $('#idskpd').val();
    var idkontrak = $('#idKontrak').val();
    
    $.getJSON(getbasepath() + "/bast/json/getKontrakRinci", {tahun: tahun, idskpd: idskpd, idkontrak:idkontrak },
    function (result) {
        
        var nilaikontrakrinci = result.aData[0]['nilaiKontrak'];
        var kontrakrinci = result.aData[0]['banyakKontrakRinci'];
        
        if ( kontrakrinci == 0 || nilaikontrakrinci < nilaikontrak){
            bootbox.alert("Silahkan Input Kontrak Rinci !");
        } else{
            
        }
        
    });
}