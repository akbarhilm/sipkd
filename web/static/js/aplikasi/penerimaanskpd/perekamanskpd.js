
urlPrintValidasi = "http://localhost:8081/printValidasi/printValidasi.jsp?";

$(document).ready(function() {
    hanyaAngka();
       $("#total").priceFormat({prefix: "", suffix: "", thousandsSeparator: ".", limit: 15,centsLimit: 0});
     
        jatuhTempoDefault = $("#jatuhTempo").val();
        tahunPajakDefault = $("#tahunPajak").val();
    
    setComboBulan();
    $("#NopdMasterForm").submit(function(event) {

        simpanTransaksi();
        event.preventDefault();


    });
    $("#btnClear").click(function() {


        clear();

 unlock();


    });
    
    
    $("#btnBatal").click(function(){
       
    //    batal();
        
    });

  /* $("#btnCari").click(function() {



           cariPenerimaan();

    });*/


});

function setNoBa(param){
    noBa = param;
}
function setTglBa(param){
    tglBa = param;
}
function setKeterangan(param){
    keterangan = param;
}
function getJatuhTempoDefault(){
    return  jatuhTempoDefault;
}


function getDateValidasi(){
    return dateValidasi;
}
function setComboBulan() {
    var d = new Date();

    var n = d.getMonth();


    $("#dateMasaAwal").prop("selectedIndex", n + 1);
    $("#dateMasaAkhir").prop("selectedIndex", n + 1);

}


function batal(){
    
     var noLoket = $("#noLoket").val();

 var json = {
        "noLoket": noLoket,
        "noBa": noBa,
        "tglBa": tglBa,
        "keterangan":keterangan,
        "idValidasi": $("#idValidasi").val(),
                "idPenerimaan": $("#idPenerimaan").val(),
                "idSts": $("#noSkpd").val()
  
    };
console.log(json);
    $.ajax({
        url: getbasepath() + "/nopd/json/batalPenerimaan",
        data: JSON.stringify(json),
        type: "POST",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function(response) {
           
           
     
        
           
              $("#btnBatal").attr('disabled',true);
           alert("Proses batal transaksi berhasil dilakukan");
            clear();
        unlock();
        }
    });
   
}
    
    
    

function cariPenerimaan() {


   
    var noLoket = $("#noLoket").val();

 var json = {
        "noLoket": noLoket
     
    };

    $.ajax({
        url: getbasepath() + "/nopd/json/cariDataPenerimaan",
        data: JSON.stringify(json),
        type: "POST",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function(response) {
            console.log(response);
           
       $("#idPenerimaan").val(response['idPenerimaan']);
       $("#noSkpd").val(response['noSkpd']);
       $("#npwpd").val(response['npwpd']);
       $("#namaNpwpd").val(response['namaNpwpd']);
       $("#alamatNpwpd").val(response['alamatNpwpd']);
       $("#jatuhTempo").val(response['jatuhTempo']);
       $("#uraian").val(response['uraian']);
        $("#namaObjekPajak").val(response['namaObjekPajak']);
     $("#alamatObjekPajak").val(response['alamatObjekPajak']);
       $("#idObjekPajak").val(response['idObjekPajak']);
       $("#tahunPajak").val(response['tahunPajak']);
       $("#idEdit").val('1');
     // $("#idSkpdPenyetor").val(response['idSkpdPenyetor']);
      $("#caraBayar").val(response['caraBayar']);
      $("#dateMasaAwal").val(response['dateMasaAwal']);
      $("#dateMasaAkhir").val(response['dateMasaAkhir']);
      $("#idValidasi").val(response['idValidasi']);
       $("#jenisSetoran").val(response['jenisSetoran']);
       dateValidasi = response['dateValidasi'];
      
       if(response['idValidasi'] == null){
            unlock();
           
         
       }else{
             lock();
 
         $("#btnBatal").removeAttr('disabled');
   
       }
         grid();
        
        }

    });
}



function cariNopd() {


   
    var nopd = $("#nopd").val();

 var json = {
        "nopd": nopd
     
    };

    $.ajax({
        url: getbasepath() + "/nopd/json/carinopd",
        data: JSON.stringify(json),
        type: "POST",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function(response) {
         $("#npwpd").val(response['npwpd'])
        $("#namaNpwpd").val(response['namaWajibPajak'])
          $("#alamatNpwpd").val(response['alamatWajibPajak'])
            $("#namaObjekPajak").val(response['namaObjekPajak'])
              $("#alamatObjekPajak").val(response['alamatObjekPajak'])
       
         grid();
        
        }

    });
}

function clear() {
    $('input.clear').removeAttr('value');
    $('#jenisSetoran').prop("selectedIndex", 0);
    $("#NopdDetailTable tbody").html("");
  //  $("#idSkpdPenyetor").prop("selectedIndex", 0);
    $("#caraBayar").prop("selectedIndex", 0);
    $("#idObjekPajak").prop("selectedIndex", 0);
    $("#totalPenerimaan").val("0");
    $("#chkBerulang").prop("checked", false);
    setComboBulan();
    $("#idEdit").val("0");
    $("#jatuhTempo").val(jatuhTempoDefault);
    $("#tahunPajak").val(tahunPajakDefault);
    
}

function clearBerulang() {

}

function lock() {
   // $('#idSkpdPenyetor').attr('readonly', 'true');
    //$('#noLoket').attr('readonly', 'true');
    $('#noSkpd').attr('readonly', 'true');
  
      $('#chkBerulang').attr('readonly', 'true');
 
    $('#jatuhTempo').attr('readonly', 'true');
    $('#caraBayar').attr('readonly', 'true');
    $('#jenisSetoran').attr('readonly', 'true');
  
    $('#dateMasaAwal').attr('readonly', 'true');
  
    $('#tahunPajak').attr('readonly', 'true');
    $('.inputmoney').attr('readonly', 'true');
      $("#btnValidasi").removeAttr('disabled');
            $("#btnSimpan").attr('disabled', 'true');
            
}

function unlock() {
 $('.inputmoney').removeAttr('readonly');
   
    $('#noSkpd').removeAttr('readonly');

   
    $('#jatuhTempo').removeAttr('readonly');
    $('#caraBayar').removeAttr('readonly');
    $('#jenisSetoran').removeAttr('readonly');
  
    $('#dateMasaAwal').removeAttr('readonly');

    $('#tahunPajak').removeAttr('readonly');
   
       $("#btnSimpan").removeAttr('disabled');
            $("#btnValidasi").attr('disabled', 'true');
$("btnBatal").attr('disabled', 'true');

}
function cetakTransaksi() {
    var idPenerimaan = $("#idPenerimaan").val();
    var idLoket = $('#idLoket').val();
    var idEdit = $('#idEdit').val();
    console.log(idEdit);
    var noValidasi;
    var json = {
        "idPenerimaan": idPenerimaan,
        "idLoket": idLoket,
        "idEdit": idEdit
    };

    $.ajax({
        url: getbasepath() + "/nopd/json/cetakData",
        data: JSON.stringify(json),
        type: "POST",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        },
        success: function(response) {
            var idValidasi = response["idValidasi"];
            $("#idValidasi").val(idValidasi);
            $("#lembar").val(response["lembar"]);
            
        
            $("#total").val(response["total"]);
            $("#total").priceFormat({prefix: "", suffix: "", thousandsSeparator: ".", limit: 15,centsLimit: 0});
   
    
           
            // var jumlah = response['jumlah'].priceFormat({prefix: "", suffix: "", thousandsSeparator: ".", limit: 15});
     var jumlah = $("#totalPenerimaan").val();
            var parsingGlobal="noValidasi="+idValidasi+"&stsNo="+response["idSts"]+"&tglSkr="+response["rekamTanggal"]+"&jmlh="+jumlah+"000&nmLoket="+response["namaLoket"]+"&caraBayar="+response["caraBayar"]+"&nmSkpd="+response["namaSkpd"]+"&jenis="+response["jenis"]+"&alamat="+response["alamat"];
            
           
            $.ajax({
        url: urlPrintValidasi+ parsingGlobal,
        
        type: "GET",
       
        error: function(response) {
            
            alert(response);
            
        }
             });
            
          alert("Proses Validasi Berhasil");
    
    
            if ($("#chkBerulang").prop("checked") == true) {
                clearBerulang();

            } else {
                clear();
            }
            unlock();
        }

    });
}
function simpanTransaksi() {
    var idPenerimaan = $('#idPenerimaan').val();
   
    var idLoket = $('#idLoket').val();
    var idSkpd = $('#idSkpd').val();
  //  var idSkpdPenyetor = $('#idSkpdPenyetor').val();
    var noSkpd = $('#noSkpd').val();
    var npwpd = $('#npwpd').val();
    var namaNpwpd = $('#namaNpwpd').val();
    var alamatNpwpd = $('#alamatNpwpd').val();
    var jatuhTempo = $('#jatuhTempo').val();
    var caraBayar = $('#caraBayar').val();
    var uraian = $('#jenisSetoran').val();
    var namaObjekPajak = $('#namaObjekPajak').val();
    var alamatObjekPajak = $('#alamatObjekPajak').val();
    var dateMasaAwal = $('#dateMasaAwal').val();
    var dateMasaAkhir = $('#dateMasaAwal').val();
    var tahunPajak = $('#tahunPajak').val();
    var jumlahRowTable = $('#jumlahRowTable').val();
   var idEdit = $('#idEdit').val();
  
    var idAkun = [];
    var inputMoney = [];
    var edit = [];
    var j = 0;
    for (var i = 1; i <= jumlahRowTable; i++) {
var x = $("#inputMoney" + i).val();
        x = x.replace(/[.]+/g,"").replace(",00","");
    
        if (x.length > 1) {
            idAkun.push($('#idAkun' + i).val());
            inputMoney.push(x);
            edit.push($('#edit'+i).val());
            j++;
        }
    
    }

    var json = {
        "idPenerimaan": idPenerimaan,
        "idLoket": idLoket,
        "idSkpd": idSkpd,
        //"idSkpdPenyetor": idSkpdPenyetor,
        "noSkpd": noSkpd,
        "npwpd": npwpd,
        "namaNpwpd": namaNpwpd,
        "alamatNpwpd": alamatNpwpd,
        "jatuhTempo": jatuhTempo,
        "caraBayar": caraBayar,
        "uraian": uraian,
        "namaObjekPajak": namaObjekPajak,
      "alamatObjekPajak": alamatObjekPajak,
        "dateMasaAwal": dateMasaAwal,
        "dateMasaAkhir": dateMasaAkhir,
        "tahunPajak": tahunPajak,
        "jumlahRowTable": jumlahRowTable,
        "idEdit": idEdit
    };
    
    var isiTable = [];
    for (var i = 0; i < j; i++) {



        isiTable.push({
            "idAkun": idAkun[i],
            "inputMoney": inputMoney[i],
            "edit": edit[i]
        });
    }

    json.isiTable = isiTable;
    console.log(json);
    var idPenerimaan;
    var noLoket;
    var noPenerimaan;
    $.ajax({
        url: getbasepath() + "/nopd/json/simpanData",
        data: JSON.stringify(json),
        type: "POST",
        beforeSend: function(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success: function(response) {
            idPenerimaan = response['idPenerimaan'];
            noLoket = response['noLoket'];

            noPenerimaan = response['noPenerimaan'];
            $("#noLoket").val(noPenerimaan);
            $("#idPenerimaan").val(idPenerimaan);
         
     
            lock();
            $("#idEdit").val("0");
            alert("proses Simpan Berhasil");
        },error: function(xhr){
            alert("An error occured: " + xhr.status + " " + xhr.statusText);
        }

    });

}

function hanyaAngka() {
    $(".inputmoney").keydown(function(e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                // Allow: Ctrl+A
                        (e.keyCode == 65 && e.ctrlKey === true) ||
                        // Allow: home, end, left, right, down, up
                                (e.keyCode >= 35 && e.keyCode <= 40)) {
                    // let it happen, don't do anything
                    return;
                }
                // Ensure that it is a number and stop the keypress
                if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                    e.preventDefault();
                }
            });

}


function grid() {
    
 $("#NopdDetailTable tbody").html("");
    $("#NopdDetailTable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#NopdDetailTable').dataTable({
            "bPaginate": false,
            //  "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "sDom": '<"top">rt<"bottom"flp><"clear">',
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": getbasepath() + "/nopd/json/listBasChild",
            "aaSorting": [[1, "asc"]],
            "fnDrawCallback": function () {
              
             $(".inputmoney").priceFormat({prefix: "", suffix: "", thousandsSeparator: ".", limit: 15,centsLimit: 0});
       
                     if( $('#idEdit').val() == 1 &&  $("#idValidasi").val().length != 0 ){
                 
                $('.inputmoney').attr('readonly', 'true');
                   
                }
             
               inputMoney();
//           //  gettotalspddanspp( );
               // getbanyakspdrinci();
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "nopd", value: $('#nopd').val()
                }   , {name: "idPenerimaan", value: $('#idPenerimaan').val()
                }        
                
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
            }, "fnInitComplete": function() {
                var totalRow = $('#jumlahRowTable', window.parent.document).val();
     
                if( $('#idEdit').val() == 1 &&  $("#idValidasi").val().length != 0 ){
                 
                $('.inputmoney').attr('readonly', 'true');
                }
    
               
            },
            "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
                var nilaiPenerimaan;
                if (aData['nilaiPenerimaan'] == 0) {
                    nilaiPenerimaan = "placeholder='0.00'";

                } else {
                    nilaiPenerimaan = "value='" + aData['nilaiPenerimaan'] + "'";

                }
                var totalNumRow = iDisplayIndexFull + 1;
                var totalRow = "<input type='hidden' name='jumlahRowTable' id='jumlahRowTable' value='" + totalNumRow + "'>";
                var inputForm = "<input type='hidden' id='edit"+ index + "' name='edit"+ index + "' "+ nilaiPenerimaan +"><input type='hidden' name='idAkun" + index + "' id='idAkun" + index + "' value='" + aData['idAkun'] + "'><input type='text' name='inputMoney" + index + "' " + nilaiPenerimaan + " id='inputMoney" + index + "' class='inputmoney ' onkeyup=inputMoney() />";
                $('div#jumlahRowTableContainer').html(totalRow);
                $('td:eq(0)', nRow).html(index);
                $('td:eq(3)', nRow).html(inputForm);



                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idAkun", "bSortable": false, sClass: "center"},
                {"mDataProp": "kodeAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "namaAkun", "bSortable": true, sDefaultContent: "-"},
                {"mDataProp": "nilaiPenerimaan", "bSortable": false, sDefaultContent: "-", sClass: "center"},
            ]
        });
inputMoney();
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
 

}
function inputMoney() {
     
   var totalRow = $('#jumlahRowTable', window.parent.document).val();
    var floor = Math.floor;
   var total = floor(0);
    for (var i = 1; i <= totalRow; i++) {
      var x = $('#inputMoney' + i).val();
        total += floor(x.replace(/[.]+/g,"").replace(",00",""));
    }
    
    $('#totalPenerimaan').val(total);
       $(".totalPenerimaan").priceFormat({prefix: "", suffix: "", thousandsSeparator: ".", limit: 15,centsLimit: 0});
     
      // 
        
       
}

