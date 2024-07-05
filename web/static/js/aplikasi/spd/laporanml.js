$(document).ready(function() {
    setTanggalAwal();
});

function getlistspdcetak(id) {
       gridspdbtlcetaklist( );
}


function gridspdbtlcetaklist() {
    //var urljson = getbasepath() + "/spd/monitoring/json/getmonitoring";
  }

function setTanggalAwal(){
    var tgl = "01/01/"+ $("#tahun").val();
    console.log("tanggal awal = "+tgl);
    $("#tgl1").val(tgl);
}

function cetakmonlap() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#kproses").val();
    var ccc = $("#tgl1").val();
    var ddd = $("#tgl2").val();
    var qqq =  ccc.split("/").join("-");
    //alert(ccc);
    //alert(ddd);
    var eee = aaa+"-"+"SPD-LAPORAN-REALISASI-ANGGARAN"+".pdf";
   // alert(eee);
   window.location.href= getbasepath() + "/spd/monitoringlaporan/prosescetaklaporan?tahun="+aaa+"&tgl1="+ccc+"&tgl2="+ddd+"&namafile="+eee;
  //alert(window.location.href);		1.20.330
   
}

function cetakmonlap1() {
    
    
    var aaa = $("#tahun").val();
    var bbb = $("#options").val();
    var ccc = $("#tgl1").val();
    var ddd = $("#tgl2").val();
    var qqq =  ccc.split("/").join("-");
   // alert(bbb);
   // alert(ddd);
    var eee = aaa+"-"+"SPD-LAPORAN-REALISASI-ASISTEN"+".pdf";
  //  alert(eee);
   window.location.href= getbasepath() + "/spd/monitoringlaporan/json/getmonlapass?tahun="+aaa+"&tgl1="+ccc+"&tgl2="+ddd+"&namafile="+eee;
  //alert(window.location.href);		1.20.330
   
}

function gridspdmonlaplist() {
    var urljson = getbasepath() + "/spd/monitoringlaporan/json/getlistmonlap";
    if (typeof myTable == 'undefined') {
        myTable = $('#btlspdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
           // "sDom": '<"top"i>lrt<"bottom"i>p<"clear">',
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                {name: "id", value: $('#idskpd').val()},
                {name: "levelSkpd", value: $('#levelSkpd').val()},
                {name: "tgl1", value: $('#tgl1').val()},
                {name: "tgl2", value: $('#tgl2').val()}
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
                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(aData['C_AKUN']);
                $('td:eq(2)', nRow).html(aData['N_AKUN']);
                $('td:eq(3)', nRow).html(accounting.formatNumber(aData['V_ANGG_TAPD']));
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['V_SPD']));
                $('td:eq(5)', nRow).html(aData['PERSEN_SPD']);
                $('td:eq(6)', nRow).html(accounting.formatNumber(aData['V_SP2D']));
                $('td:eq(7)', nRow).html(aData['PERSEN_SP2D']);
                $('td:eq(8)', nRow).html(aData['PERSEN_SP2D_TAPD']);
             
               
                var namafile = "SPD" + aData['JENIS'] + "." + aData['C_ANGG_TAHUN'] + "." + aData['NOSPD'] + ".pdf"
                
               
                return nRow;
            },
            "fnDrawCallback": function() {
                // $(".checkbox").hide();
            },
            "aoColumns": [
                {"mDataProp": "C_AKUN", "bSortable": false, sClass: "center"},
                {"mDataProp": "C_AKUN", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "N_AKUN", "bSortable": false, sClass: "left"},
                {"mDataProp": "V_ANGG_TAPD", "bSortable": false, sClass: "right", sDefaultContent: "-"},
                {"mDataProp": "V_SPD", "bSortable": false, sClass: "right", sDefaultContent: "-"},
                {"mDataProp": "PERSEN_SPD", "bSortable": false, sClass: "right"},
                {"mDataProp": "V_SP2D", "bSortable": false, sClass: "right", sDefaultContent: "-"},
                {"mDataProp": "PERSEN_SP2D", "bSortable": false, sClass: "right", sDefaultContent: "-"},
                {"mDataProp": "PERSEN_SP2D_TAPD", "bSortable": false, sClass: "right", sDefaultContent: "-"},
                
            ]
        });
       // $("div.top").html("<button onclick=trigercetak() class='btn .dark-stripe' style='float: right'>Proses Cetak</button>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


