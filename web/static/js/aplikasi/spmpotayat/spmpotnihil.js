
$(document).ready(function() {

    //$("#btlspdtablebody").hide();
    gettotalspm($("#nospm").val());
    //getnilaikontrak($("#nospm").val());
    gridsppup();
    console.debug("lolololo");
});

var varpersen = new Array();
var nilaikontrak;
var nilaijamsostek;
var nilaippn10persen;
var nilaipphps22;
var addoredit = new Array();

function gettotalspm(idspm) {

    $.getJSON(getbasepath() + "/spmpotnonayat/json/totspmjson/" + idspm, {idskpd: $('#idskpd').val(), tahun: $('#tahunAnggaran').val()},
    function(result) {
        $('#totspm').val(accounting.formatNumber(result.aData[0]['totSpm'], 2, '.', ","));
        $('#totspmhide').val(result.aData[0]['totSpm']);
        //getValTabel(idspm);
        hitung();
    });
}

function getValTabel(idspm) {

    $.getJSON(getbasepath() + "/spmpotnonayat/json/valtabel/" + idspm, {idskpd: $('#idskpd').val(), tahun: $('#tahunAnggaran').val()},
    function(result) {
        // console.log(result)
        var i, c;
        var persenCek;

        for (i = 0; i < 10; i++) {

            c = i + 1;
            varpersen[c] = result.aData[i]['pPot'];
            totspm = $('#totspmhide').val();

            addoredit[c] = result.aData[i]['kodeEdit'];
            //alert(totspm);
            if (c == 1) {

            }
            else if (c == 2) {
            } else {
                //console.log(" c "+c+" = "+result.aData[i]['nilaiPot'] )
                $('#vpot' + c).val(accounting.formatNumber(result.aData[i]['nilaiPot'], 2, '.', ","));
            }
            $("#tampildataholder" + c).append(result.aData[i]['pot']);
            $('#cpot' + c).val(result.aData[i]['cPot']);
            $('#status' + c).val(result.aData[i]['status']);

            if (c == 6) {
                var cekstatusppn = result.aData[i]['statusPPN'];
                //varpersen[5] = cekstatusppn == 1?result.aData[i]['pPot']/10:result.aData[i]['pPot'];
                varpersen[6] = result.aData[i]['pPot'];
                $("#cekpersen6status").val(cekstatusppn);


            } else if (c == 10) {
                var cekstatusppn = result.aData[i]['statusPPN'];
                $("#cekpersen10").val(cekstatusppn);
                //varpersen[9] = cekstatusppn == 1?result.aData[i]['pPot']/10:result.aData[i]['pPot'];
                varpersen[10] = result.aData[i]['pPot'];
                //console.log("Persen 10 = "+result.aData[i]['pPot']);
                //console.log("cekstatusppn = "+cekstatusppn);
            }

            //console.log("index "+c+" - keterangan add or edit ==== "+addoredit[c]);

        }

        setPPN10(result.aData[0]['nilaiPot']);
        setPPH22(result.aData[1]['nilaiPot']);

        nilaippn10persen = $('#vpot1').val();
        nilaipphps22 = $('#vpot2').val();

        /*
         var nilaiasuransi = result.aData[2]['nilaiPot'];
         var banyak = nilaiasuransi/20000;
         
         if (nilaiasuransi > 0){
         $("#asuransi3").val(banyak);
         }
         */
        setJamsostek();

        persenCek = varpersen[6];
        
        if (persenCek == ".5") {
            persenCek = "0.5";
        }
        
        if (varpersen[10] == ".5") {
            varpersen[10] = "0.5";
        }
        
        
        document.getElementById("persen6").value = persenCek; //varpersen[5];
        document.getElementById("persen10").value = varpersen[10];


    });

    $("#btlspdtablebody").show();

}

function hitung() {
    var totspm, dati;
    totspm = $('#totspmhide').val();
    dati = (totspm / 1.1) * (10 / 100);
    $('#vpot1').val(accounting.formatNumber(dati, 2, '.', ","));
    dati = (totspm / 1.1) * (1.5 / 100);
    $('#vpot2').val(accounting.formatNumber(dati, 2, '.', ","));
}

function hitung6() {
    var totspm, dati, persen;
    var cekstatus = $("#cekpersen6status").val();
    var pengali = cekstatus == 0 ? 1 : 1.1;
    totspm = $('#totspmhide').val();
    persen = $("#persen6").val();
    dati = (totspm / pengali) * (persen / 100);
    $('#vpot' + 6).val(accounting.formatNumber(dati, 2, '.', ","));
    varpersen[6] = $("#persen6").val();
}

function hitung10() {
    var totspm, dati, persen;
    var cekstatus = $("#cekpersen10").val();
    var pengali = cekstatus == 0 ? 1 : 1.1;
    //var pengali = 1.1; // default PPN
    totspm = $('#totspmhide').val();
    persen = $("#persen10").val();
    dati = (totspm / pengali) * (persen / 100);
    console.log(" pengali " + pengali + "  cekstatus " + cekstatus + " dati " + dati)
    $('#vpot' + 10).val(accounting.formatNumber(dati, 2, '.', ","));
    varpersen[10] = $("#persen10").val();
}

function hitungasuransi() {
    var jumlah, banyak;
    banyak = $("#asuransi3").val();
    jumlah = (banyak * 20000);

    $('#vpot3').val(accounting.formatNumber(jumlah, 2, '.', ","));
}

function validateNumber(evt) {
    var e = evt || window.event;
    var key = e.keyCode || e.which;

    if (!e.shiftKey && !e.altKey && !e.ctrlKey &&
            // numbers   
            key >= 48 && key <= 57 ||
            // Numeric keypad
            key >= 96 && key <= 105 ||
            // Backspace and Tab and Enter
            key == 8 || key == 9 || key == 13 ||
            // Home and End
            key == 35 || key == 36 ||
            // left and right arrows
            key == 37 || key == 39 ||
            // Del and Ins
            key == 46 || key == 45) {
        // input is VALID
    }
    else {
        // input is INVALID
        e.returnValue = false;
        if (e.preventDefault)
            e.preventDefault();
    }
}

function cekmoney() {
   var nspm = parseFloat(accounting.unformat($("#totspm").val(),","));
   console.debug(nspm);
   var sum = parseFloat(accounting.unformat($("#nilai1").val(),",")) + parseFloat(accounting.unformat($("#nilai2").val(),",")) + parseFloat(accounting.unformat($("#nilai3").val(),",")) + parseFloat(accounting.unformat($("#nilai4").val(),","));
   console.debug(sum);
   if (nspm != sum){
       bootbox.alert("Nilai potongan tidak boleh lebih atau kurang dari total spm");
   }else if(nspm == sum){
       ceksubmitnilai();
   }else{
       alert("error");
   } 
}

function ceksubmitnilai() {
    submitnilai( );
}

function submitnilai( ) {
    var varurl = getbasepath() + "/spmpotnihil/json/prosespindahsimpan";
    var dataac = [];
    //var cekps22 = $("#cekpphps22status").val();
   // var persenps22 = cekps22 == 0 ? 1.5 : 1.5;
  
    var datapeg = {
        cpot1: $("#idbas1").val(),
        cpot2: $("#idbas2").val(),
        cpot3: $("#idbas3").val(),
        cpot4: $("#idbas4").val(),
        
        vpot1: $("#nilai1").val(),
        vpot2: $("#nilai2").val(),
        vpot3: $("#nilai3").val(),
        vpot4: $("#nilai4").val(),
       addoredit1: addoredit[1],
        addoredit2: addoredit[2],
        addoredit3: addoredit[3],
        addoredit4: addoredit[4],
       
        //cekpphps22status: $("#cekpphps22status").val(),
        //cekpersen6status: $("#cekpersen6status").val(),
       // cekpersen10: $("#cekpersen10").val(),
        idspm: $("#nospm").val()
    }
    dataac = datapeg;

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
        //grid();
        bootbox.alert("Data SPM Potongan Ayat berhasil disimpan");
    });
}

function settgl() {
    var isi = $("#ssspm").val();
    var tahun = isi.substr(0, 4);
    var bulan = isi.substr(5, 2);
    var hari = isi.substr(8, 2);
    var hasil = hari + "/" + bulan + "/" + tahun;

    $("#sispm").val(hasil);
    // alert("#sspm");
}

function cek() {

    //bootbox.alert("Nilai kontrak = " +nilaikontrak+ " || Nilai jamsostek = " + nilaijamsostek);
    bootbox.alert("Nilai ppn 10 = " + $('#vpot1').val());
}

function getnilaikontrak(idspm) {

    $.getJSON(getbasepath() + "/spmpotnonayat/json/nilaikontrak/" + idspm, {idskpd: $('#idskpd').val(), tahun: $('#tahunAnggaran').val()},
    function(result) {
        nilaikontrak = result.aData[0]['nilaiKontrak'];

        getjamsostek(nilaikontrak);
    });
}

function getjamsostek(nilaikontrak) {
    $.getJSON(getbasepath() + "/spmpotnonayat/json/getjamsostek", {nilaikontrak: nilaikontrak},
    function(result) {
        nilaijamsostek = result.aData[0]['nilaiJamsostek'];
    });
}

function hitungPPN10() {
    var hasil;
    totspm = $('#totspmhide').val();
    hasil = (totspm / 1.1) * (10 / 100);

    if (document.getElementById("cekppn10").checked == true) {
        $('#vpot1').val(accounting.formatNumber(hasil, 2, '.', ","));

    } else {
        //bootbox.alert("NOL?");
        $('#vpot1').val(0);
    }
}

function hitungPPHPS22() {
    var hasil;
    totspm = $('#totspmhide').val();
    var statuspph22 = $("#cekpphps22status").val();
    if (statuspph22 == 0) {
        hasil = (totspm) * (1.5 / 100);
    } else {
        hasil = (totspm / 1.1) * (1.5 / 100);
    }



    if (document.getElementById("cekpphps22").checked == true) {
        $('#vpot2').val(accounting.formatNumber(hasil, 2, '.', ","));

    } else {
        $('#vpot2').val(0);
    }
}

function hitungJamostek() {
    if (document.getElementById("cekjamsostek").checked == true) {
        if (nilaikontrak >= 110000000) {
            $('#vpot5').val(accounting.formatNumber(nilaijamsostek, 2, '.', ","));
        } else {
            nilaijamsostek = 0.0024 * (nilaikontrak / 1.1);
            $('#vpot5').val(accounting.formatNumber(nilaijamsostek, 2, '.', ","));
        }
    } else {
        $('#vpot5').val(0);
    }
}

function setJamsostek() {
    var nilai = parseFloat(accounting.unformat($('#vpot5').val(), ",")); // jamsostek

    if (nilai !== 0) {
        $("#cekjamsostek").prop("checked", true);
    } else {
        $("#cekjamsostek").prop("checked", false);
    }

}

function setPPN10(nilai) {

    if (nilai !== 0) {
        $("#cekppn10").prop("checked", true);
    } else {
        $("#cekppn10").prop("checked", false);

    }
    $('#vpot1').val(accounting.formatNumber(nilai, 2, '.', ","));
    //$('#vpot1').val(nilai);

}

function setPPH22(nilai) {

    if (nilai !== 0) {
        $("#cekpphps22").prop("checked", true);
    } else {
        $("#cekpphps22").prop("checked", false);

    }
    $('#vpot2').val(accounting.formatNumber(nilai, 2, '.', ","));
    //$('#vpot2').val(nilai);
    //---------
    var nilaitotspm = parseFloat($('#totspmhide').val());
    var vpot = nilai;

    var hasilnonppn = vpot == (nilaitotspm) * (1.5 / 100) ? 0 : 1;
    $('#cekpphps22status').val(hasilnonppn).change();

}

function gridsppup() {
    var urljson = getbasepath() + "/spmpotnihil/json/getlistnihil";
    // var urljson = getbasepath() + "/sppup/json/getlistspdbl";
    if (typeof myTable == 'undefined') {
        myTable = $('#blspdtable').dataTable({
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
                        {name: "idskpd", value: $('#idskpd').val()},
                  {name: "idspm", value: $('#nospm').val()}
                //{name: "tahun", value: $('#tahun').val()}
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
                addoredit[index] = aData['kodeEdit'];
//                var batas = aData['batas'];
//                var d = new Date();
//                var tgl = d.getUTCDate();
//                var bln = d.getMonth() + 1;
//                
//                console.log("batas = "+batas);
//                console.log("tgl = "+tgl);
//                console.log("bln = " + bln);
//                
//                var edit = "<a href='" + getbasepath() + "/spmblls/editspmblls/" + aData['id'] + "'  title='Klik disini untuk melakukan entry/update SPM' class='icon-edit' ></a>";
//                var potongannonayat = " - ";
//                var potonganUangMuka = " - ";
//                var potongannihil = " - ";
//
//                if (tgl > batas && bln == 12) { // tambahkan validasi bulan, edit 19 jan 16 by zainab
//                    //console.log("masuk batas = ");
//                    potongannonayat = " - ";
//                    potonganUangMuka = " - ";
//                    potongannihil = " - ";
//                } else {
//                   // console.log("masuk else = ");
//                    if (aData['potongan'] == true) {
//                        potongannonayat = "<a href='" + getbasepath() + "/spmpotnonayat/indexspm/" + aData['idspm'] + "'  title='Klik disini untuk melakukan entry/update Potongan SPM' class='icon-edit' ></a>";
//                    }
//                    if (aData['potonganUangMuka'] == true) {
//                        potonganUangMuka = "<a href='" + getbasepath() + "/spmpotuangmuka/indexspm/" + aData['idspm'] + "'  title='Klik disini untuk melakukan entry/update Potongan SPM' class='icon-edit' ></a>";
//                    }
//                    if (aData['potonganNihil'] == true) {
//                        potongannihil = "<a href='" + getbasepath() + "/spmpotnihil/indexspm/" + aData['idspm'] + "'  title='Klik disini untuk melakukan entry/update Potongan SPM' class='icon-edit' ></a>";
//                    }
//                }

//                var batal = "";
//                if (aData['idspm']) {
//                    batal = "<a title='Klik disini untuk membatalkan spm' class='icon-remove linkpilihan' href='#' onclick=hapusspm(" + aData['idspm'] + ",'" + aData['noSpm'] + "') ></a>";
//
//                }
                  var idbas = "<input type='hidden' id=idbas"+index+" value="+aData['idBas']+">";
                  var nilai = "<input type = 'text' class='inputnumber inputmoney' onkeyup='formatnumberonkeyup()' id=nilai"+index+" value="+accounting.formatNumber(aData['nilaiPot'])+">";
               // var namakegiatan = "<textarea style='border: none;width: 99%'>" + aData['namaKegiatan'] + "</textarea>"
               // $('td:eq(4)', nRow).html(namakegiatan);
                $('td:eq(0)', nRow).html(index+idbas);
                $('td:eq(2)', nRow).html(nilai);
               // $('td:eq(3)', nRow).html(edit + "    " + batal);
               // $('td:eq(6)', nRow).html(potongannonayat);
               // $('td:eq(7)', nRow).html(potonganUangMuka);
               // $('td:eq(8)', nRow).html(potongannihil);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "5%"},
                {"mDataProp": "namaAkun", "bSortable": false, sClass: "left", sDefaultContent: "-", "sWidth": "8%"},
                {"mDataProp": "nilaiPot", "bSortable": false, sClass: "kanan", sDefaultContent: "-", "sWidth": "10%"}
               
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}

 