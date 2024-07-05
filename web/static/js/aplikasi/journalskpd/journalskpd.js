$(document).ready(function() {
    getbanyakrinci();
    setunit();
    gridskpd();
    
});

var idrow = 0;
var banyaktampil = 0;
var noNR = 0;
var banyakdata;
var kosong = 0;
var totaldebetNR;
var totalkreditNR;
var totaldebet;
var totalkredit;
var grandtotalD = 0;
var grandtotalK = 0;
var idbas = new Array();
var idbaslist = new Array();
var idpilih;
var unitawal;

var banyakdataawal;

var nilaid_arr = new Array();
var nilaik_arr = new Array();
var nilaid_awal = new Array();
var nilaik_awal = new Array();
var nilaisaldo_arr = new Array();
var nilaisaldo_nr = new Array();

function getbanyakrinci() {
    $.getJSON(getbasepath() + "/journalskpd/json/getlistbanyakrinci", {idskpd: $("#unit").val(), tahun: $("#tahun").val()},
    function(result) {
        banyakdata = result;
        banyakdataawal=result;
        noNR = result;
        $('#banyakrinci').val(result);
    });
}

function setunit(){
    unitawal =  $('#unit').val();
}

function clearnilai(){
    var i;

    for (i = 1; i <= banyakdataawal; i++) {
        nilaid_arr[i] = 0;
        nilaik_arr[i] = 0;
    }
}

function gridskpd( ) {
     gettotal();
     clearnilai();
     
     var unitb = $('#unit').val();
     
     if (unitb == unitawal){
        
     } else {
        banyaktampil = 0;
        unitawal = unitb;
        //bootbox.alert("UNIT BEDA ---- Current Unit = "+ unitb );
     }
     
     $("#jourskpdtable").show();
     $("#jourskpdtablefoot2").empty();
    
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
            "aLengthMenu": [[50, 100, -1], [50, 100, "All"]],
            "iDisplayLength": 50,
            "sAjaxSource": getbasepath() + "/journalskpd/json/journalskpd",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function() {
                formatnumberonkeyup();
                $(".inputmoney").autoNumeric('init', {aSep: '.', aDec: ','});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "tahun", value: $('#tahun').val()},
                {name: "idskpd", value: $('#unit').val()}
                );
            },
            "fnFooterCallback": function(nRow, aaData, iStart, iEnd, iDisplay) {  
                gettotal();
               
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
                var nilaiD = aData['nilaiDebet'];
                var nilaiK = aData['nilaiKredit'];
                var nilaidebtext = "<input type='text' name='nilaidebet" + index + "' id='nilaidebet" + index + "'  style='text-align: right'   class='inputmoney'  value='" + nilaiD + "' onkeyup='hitungtotaldebet(" + index + ",this.value),validasiDebet(" + index + ",this.value)' onclick = 'validasiDebetKlik(" + index + ")' />"
                var nilaidebetorg = "<input type='hidden' id='nilaidebetorg" + index + "' value='" + nilaiD + "' />"
                var nilaikretext = "<input type='text' name='nilaikredit" + index + "' id='nilaikredit" + index + "'  style='text-align: right'   class='inputmoney'  value='" + nilaiK + "' onkeyup='hitungtotalkredit(" + index + ",this.value),validasiKredit(" + index + ",this.value)' onclick = 'validasiKreditKlik(" + index + ")'/>"
                var nilaikretextorg = "<input type='hidden' id='nilaikreditorg" + index + "' value='" + nilaiK + "' />"
                var pilih = "";
                
                idbaslist[index]=aData['idBas'];
                nilaid_arr[index] = nilaiD;
                nilaik_arr[index] = nilaiK; 
                nilaid_awal[index] = nilaiD;
                nilaik_awal[index] = nilaiK; 
                
                if (banyaktampil < banyakdata){
                    banyaktampil += 1;
                }
                
                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(pilih);
                $('td:eq(4)', nRow).html(nilaidebtext + nilaidebetorg);
                $('td:eq(5)', nRow).html(nilaikretext + nilaikretextorg);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"},
                {"mDataProp": "idBas", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeAkun", "bSortable": false, sClass: "left"},
                {"mDataProp": "namaAkun", "bSortable": false, sClass: "left"},
                {"mDataProp": "nilaiDebet", "bSortable": false, sClass: "kanan"},
                {"mDataProp": "nilaiKredit", "bSortable": false, sClass: "kanan"},
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}

function gettotal() {
    var idskpd = $("#unit").val();
    var tahun = $("#tahun").val();

    $.getJSON(getbasepath() + "/journalskpd/json/gettotal", {idskpd: idskpd, tahun: tahun},
    function(result) {

        var td = result.aData[0]['totalDebet'];
        var tk = result.aData[0]['totalKredit'];
        $("#totdebet").val(accounting.formatNumber(td, 2, '.', ","));
        $("#totkredit").val(accounting.formatNumber(tk, 2, '.', ","));
        totaldebet = td;
        totalkredit = tk;
    });
}

function hitungtotaldebet(idx,nilai){ 
    var total,dawal,dakhir;
    
    dawal = parseFloat(accounting.unformat(nilaid_arr[idx], ","));
    dakhir = parseFloat(accounting.unformat(nilai, ","));
    total = totaldebet + dakhir - dawal;
   
    nilaid_arr[idx] = nilai;
    totaldebet = total;
    
    hitunggrandtotal();
}

function hitungtotalkredit(idx,nilai){ 
    var total,dawal,dakhir;
    
    dawal = parseFloat(accounting.unformat(nilaik_arr[idx], ","));
    dakhir = parseFloat(accounting.unformat(nilai, ","));
    total = totalkredit + dakhir - dawal;
    
    nilaik_arr[idx] = nilai;
    totalkredit = total;
    
    hitunggrandtotal();
}

function hitunggrandtotal(){
    grandtotalD = 0;
    grandtotalK = 0;
    
    if (idrow < 1){
        totaldebetNR=0;
        totalkreditNR=0;
    }
    
    grandtotalD = totaldebet + totaldebetNR;
    grandtotalK = totalkredit + totalkreditNR;
    
    $("#totdebet").val(accounting.formatNumber(grandtotalD, 2, '.', ","));
    $("#totkredit").val(accounting.formatNumber(grandtotalK, 2, '.', ","));
}

function getidakun() {
    idbas[idpilih] = $('#idbaspop').val();
    $('#kodeakunNR' + idpilih).val($('#idakunpop').val());
    $('#namaakunNR' + idpilih).val($('#namaakunpop').val());
}

function getidpilih(id) {
    idpilih = id.substring(9);
}

function cariakun(kode, evt, nama) {
    var e = evt || window.event;
    var key = e.keyCode || e.which;

    if (!e.shiftKey && !e.altKey && !e.ctrlKey && key == 13) {
        idbas[idpilih] = $('#idbaspop').val();
        getnama(kode, nama);
    }
}

function getnama(kode, nama) {
    
    $.getJSON(getbasepath() + "/journalskpd/json/getnamaakun", {kode: kode},
    function(result) {
        var id = nama.substring(10);
        
        if (result.aData.length > 0) {
            var namaakun = result.aData[0]['namaAkun'];
            var idbasval = result.aData[0]['idBas'];

            $('#namaakunNR' + id).val(namaakun);
            idbas[id] = idbasval;
            
        } else {
            bootbox.alert("Kode Akun Tidak Tersedia!");
            $('#namaakunNR' + id).val("");
            $('#kodeakunNR' + id).val("");
        }
    });
}

function validasiDebetKlik(id) {
    var nilai;
    nilai = parseFloat(accounting.unformat($('#nilaidebet' + id).val(), ","));
    
    if ($('#nilaidebet' + id).val() !== 0) {
        hitungtotalkredit(id,0);
        hitungtotaldebet(id,nilai);
        
        parseFloat(accounting.unformat($('#nilaikredit' + id).val(0), ","));
    }
}

function validasiKreditKlik(id) {
    var nilai;
    nilai = parseFloat(accounting.unformat($('#nilaikredit' + id).val(), ","));
    
    if ($('#nilaikredit' + id).val() !== 0) {
        hitungtotaldebet(id,0);
        hitungtotalkredit(id,nilai);
        
        parseFloat(accounting.unformat($('#nilaidebet' + id).val(0), ","));
    }
}


function validasiDebet(id,nilai) {
    if ($('#nilaidebet' + id).val() !== 0) {
        hitungtotalkredit(id,0);
        hitungtotaldebet(id,nilai);
        
        parseFloat(accounting.unformat($('#nilaikredit' + id).val(0), ","));
    }
}

function validasiKredit(id,nilai) {
    if ($('#nilaikredit' + id).val() !== 0) {
        hitungtotaldebet(id,0);
        hitungtotalkredit(id,nilai);
        
        parseFloat(accounting.unformat($('#nilaidebet' + id).val(0), ","));
    }
}

function cekidbas() {
    var i;
    kosong = 0;
    
    for (i = 1; i <= idrow; i++) {
        if (idbas[i] == ""){
            kosong = 1;
        }
    }
}

function submitnilai() {
    var i;
    cekidbas();
    
    if (grandtotalD == grandtotalK) {
        if (kosong == 1){
            bootbox.alert("Kode Akun Tidak Boleh Kosong");
            kosong = 0;
        } else {
            
            var varurl = getbasepath() + "/journalskpd/json/prosessimpan";
            var dataac = [];
            var datajour;
            var nilailist = [];
            var nilainr = [];
            var nb,nk,nba,nka;

            for (i = 0; i < banyaktampil; i++) { // list
                
                nb = parseFloat(accounting.unformat(nilaid_arr[i+1], ","));
                nk = parseFloat(accounting.unformat(nilaik_arr[i+1], ","));
                nilaisaldo_arr[i+1] = nb+nk;
                
                var pararr = {nilaiDebet:  (accounting.unformat(nilaid_arr[i+1], ",")),
                    nilaiKredit:  (accounting.unformat(nilaik_arr[i+1], ",")),
                    idBas: idbaslist[i+1],
                    nilaiSaldo : nilaisaldo_arr[i+1]
                };
                nilailist[i] = pararr;
            }
            
            if (idrow > 0){
                for (i = 0; i < idrow; i++) { // new row
                    nb = parseFloat(accounting.unformat($('#nilaidebetNR' + i+1).val(), ","));
                    nk = parseFloat(accounting.unformat($('#nilaikreditNR' + i+1).val(), ","));
                    nilaisaldo_nr[i+1] = nb+nk;

                    var pararrnr = {nilaiDebet:  (accounting.unformat($('#nilaidebetNR' + (i+1)).val(), ",")),
                        nilaiKredit:  (accounting.unformat($('#nilaikreditNR' + (i+1)).val(), ",")),
                        idBas: idbas[i+1],
                        nilaiSaldo : nilaisaldo_nr[i+1]
                    };
                    nilainr[i] = pararrnr;
                }
            }
            
            datajour = {
                idrow : idrow,
                idskpd: $("#unit").val(),
                tahun: $("#tahun").val(),
                nilainr : nilainr,
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
                bootbox.alert("Data Journal Berhasil Disimpan");
            });
            
        } 
    } else {
        bootbox.alert("Data Journal Gagal Disimpan (Total Debet Tidak Sama Dengan Total Kredit)");
    }
}

function tambahRow() {
    idrow += 1;
    noNR += 1; 
   
    var table = document.getElementById('jourskpdtablefoot2');
    var row = table.insertRow();
    
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);

    cell1.innerHTML = noNR.toString();
    cell2.innerHTML = "<a id='akunpilih" + idrow + "' class='fancybox fancybox.iframe FontSmall' onclick='getidpilih(this.id)' href='" + getbasepath() + "/akun/listakunsalsoawal?target='_top'' title='Pilih Kode Akun'  ><i class='icon-zoom-in'></i> Pilih</a>";
    cell3.innerHTML = "<input name='kodeakunNR" + idrow + "' id='kodeakunNR" + idrow + "' onkeypress='cariakun(this.value,this.input,this.id)' value= '' />";
    cell4.innerHTML = "<input name='namaakunNR" + idrow + "' id='namaakunNR" + idrow + "'  readonly ='true' size='50' value= '' />";
    cell5.innerHTML = "<input type='text' name='nilaidebetNR" + idrow + "' id='nilaidebetNR" + idrow + "' class='inputmoney'  onkeyup='validasidebetNR(" + idrow + ")' onclick = 'validasidebetNR(" + idrow + ")' />";
    cell6.innerHTML = "<input type='text' name='nilaikreditNR" + idrow + "' id='nilaikreditNR" + idrow + "' class='inputmoney'  onkeyup='validasikreditNR(" + idrow + ")' onclick = 'validasikreditNR(" + idrow + ")' />";
    
    formatnumberonkeyup();
    idbas[idrow] = '';
    
}

function tampilCek(){
    if (idrow > 0){
        for (i = 0; i < idrow; i++) { 
            bootbox.alert("Nilai debet yang ke -" + (i+1)+ " === "+ $('#nilaidebetNR' + (i+1)).val());
        }
    } 
}

function hitungtotalNR(){
    var totald=0;
    var totalk=0;
    var i;
    
    for (i = 1; i <= idrow; i++) {
        totald += parseFloat(accounting.unformat($('#nilaidebetNR' + i).val(), ","));
        totalk += parseFloat(accounting.unformat($('#nilaikreditNR' + i).val(), ","));
    }
    
    totaldebetNR = totald;
    totalkreditNR = totalk;
    
    hitunggrandtotal();
}

function validasikreditNR(id){
    if ($('#nilaikreditNR' + id).val() !== 0) {
        parseFloat(accounting.unformat($('#nilaidebetNR' + id).val(0), ","));
        hitungtotalNR();
    }
}

function validasidebetNR(id){
    if ($('#nilaidebetNR' + id).val() !== 0) {    
        parseFloat(accounting.unformat($('#nilaikreditNR' + id).val(0), ","));
        hitungtotalNR();
    }
}
    