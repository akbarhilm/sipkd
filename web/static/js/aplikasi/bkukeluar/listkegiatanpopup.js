$(document).ready(function () {
    
    setidskpd()
    //grid();

});

//var id_value;

function setidskpd(){
    var id_value = window.localStorage.getItem("idskpdval");
    $('#idskpd').val(id_value);
    //var kdwilproses_value = window.localStorage.getItem("wilayahsp2dproses");
    //$('#kodewilayahproses').val(kdwilproses_value);
    grid();
}
/*
function ambilkegiatan(id) {
    //alert(id);
   //// $('#idSpd', window.parent.document).val($("#idSpd" + id).val());
   // $("#nospd", window.parent.document).text($("#noSpd" + id).val());
    $('#idKegiatan', window.parent.document).val($("#idKegiatan" + id).val());
    $('#namaKeg', window.parent.document).val($("#namaKegiatan" + id).val());
    //$('#nilaiSpd', window.parent.document).val($("#nilaiSpd" + id).val());
  //  $('#nilaiKontrakOrg', window.parent.document).val($("#nilaiSpd" + id).val());
   // $('#idKegiatan', window.parent.document).val(id).change();
    $('#idKegiatan', window.parent.document).val($("#idKegiatan" + id).val()).change();;


    parent.$.fancybox.close();
}*/

function ambilskpd(id) {
    $('#namaKeg', window.parent.document).val($("#namaKegiatan" + id).val()).change();
    $('#idKegiatan', window.parent.document).val(id).change();
    
    parent.$.fancybox.close();
}
    
function grid( ) {
    var urljson = getbasepath() + "/laporanbkukeluar/json/listpopupkegiatan";
    $("#skpdtable").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#skpdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            //"sScrollY": ($(window).height() * 108 / 100),
            "bFilter": false,
            "sAjaxSource": urljson,
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "nama", value: $('#namakegiatan').val()},
                {name: "kode", value: $('#kodekegiatan').val()},
                {name: "idskpd", value: $('#idskpd').val()},
                //{name: "kodewilproses", value: $('#kodewilayahproses').val()},
                {name: "tahun", value: $('#tahun').val()}
                );
            },
            "fnServerData": function (sSource, aoData, fnCallback) {
                $.ajax({
                    "dataType": 'json',
                    "type": "GET",
                    "url": sSource,
                    "data": aoData,
                    "success": fnCallback
                });
            },
            "fnRowCallback": function (nRow, aData, iDisplayIndex, iDisplayIndexFull, oSettings) {
                var numStart = this.fnPagingInfo().iStart;
                var index = numStart + iDisplayIndexFull + 1;
               
                var textnama = "<input type='hidden' id='nama" + aData['idKegiatan'] + "' value='" + aData['namaKeg']+"' />";
                var textkode = "<input type='hidden' id='kode" + aData['idKegiatan'] + "' value='" + aData['kodeKeg']+"' />";
                var textnamakegiatan = "<input type='hidden' id='namaKegiatan" + aData['idKegiatan'] + "' value='" + aData['kodeKeg']+"/"+aData['namaKeg'] + "' />";
                
                $('td:eq(0)', nRow).html(index);
                
                $('td:eq(3)', nRow).html(textnamakegiatan+textkode+textnama+"<span class='icon-ok-sign linkpilihan' onclick='ambilskpd(" + aData['idKegiatan'] + ")'></span>");
                
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "-"},
                {"mDataProp": "kodeKeg", "bSortable": true, sClass: "-"},
                {"mDataProp": "namaKeg", "bSortable": true, sClass: "-", sDefaultContent: "-"},
                {"mDataProp": "idKegiatan", "bSortable": false, sClass: "center"}

            ]

        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


