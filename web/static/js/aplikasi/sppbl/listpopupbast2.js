$(document).ready(function() {
   grid(); 
});
function ambilbast(id) {
   $('#idKegiatan', window.parent.document).val(id).change();
   $('#namaKegiatan', window.parent.document).val($("#kodeKegiatan" + id).val()+"/"+     $("#namaKegiatan" + id).val());
   $('#nilaiKontrak', window.parent.document).val($("#nilaiKontrak"+ id).val());
   //console.log(" nilai kontrak "+$("#nilaiKontrak"+ id).val());
   $('#noKontrak', window.parent.document).val($("#noKontrak"+ id).val());
   $('#idKontrak', window.parent.document).val($("#idKontrak"+ id).val());
   $('#noBast', window.parent.document).val($("#noBast"+ id).val());
   $('#idBast', window.parent.document).val($("#idBast"+ id).val());
   $('#idAkun', window.parent.document).val($("#idAkun"+ id).val());

   
   parent.$.fancybox.close();
}
function grid( ) {
    var urljson = getbasepath()+"/bl/json/listbastpopup2";
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
            "fnServerParams": function(aoData) {
                aoData.push(
                            {name: "idskpd", value: $('#idskpd').val()},
                            {name: "tahun", value: $('#tahun').val()}
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
                var kodeKegiatan = "<input type='hidden' id='kodeKegiatan" + aData['kegiatan']['idKegiatan'] + "' value='" + aData['kegiatan']['kodeKegiatan'] + "' />";
                var namaKegiatan = "<input type='hidden' id='namaKegiatan" + aData['kegiatan']['idKegiatan'] + "' value='" +  aData['kegiatan']['namaKegiatan'] + "' />";
                var namaRekanan =  "<input type='hidden' id='namaRekanan" + aData['kegiatan']['idKegiatan'] + "' value='" +  aData['rekanan']['namaRekanan'] + "' />";
                var noKontrak =  "<input type='hidden' id='noKontrak" + aData['kegiatan']['idKegiatan'] + "' value='" +  aData['kontrak']['noKontrak'] + "' />";
                var idKontrak =  "<input type='hidden' id='idKontrak" + aData['kegiatan']['idKegiatan'] + "' value='" +  aData['kontrak']['idKontrak'] + "' />";
                var nilaiKontrak =  "<input type='hidden' id='nilaiKontrak" + aData['kegiatan']['idKegiatan'] + "' value='" +  aData['kontrak']['nilaiKontrak'] + "' />";
                var noBast =  "<input type='hidden' id='noBast" + aData['kegiatan']['idKegiatan']+ "' value='" +  aData['bast']['noBast'] + "' />";
                var idBast =  "<input type='hidden' id='idBast" + aData['kegiatan']['idKegiatan']+ "' value='" +  aData['bast']['idBast'] + "' />";
                var idAkun =  "<input type='hidden' id='idAkun" + aData['kegiatan']['idKegiatan']+ "' value='" +  aData['akun']['idAkun'] + "' />";
                var ceklis =  "<span class='icon-ok-sign linkpilihan></span>";
                $('td:eq(0)', nRow).html(index);
                $('td:eq(6)', nRow).html(kodeKegiatan + namaKegiatan + namaRekanan + noKontrak + noBast + nilaiKontrak + idBast + idKontrak + idAkun + "<span class='icon-ok-sign linkpilihan' onclick='ambilbast(" + aData['kegiatan']['idKegiatan'] + ")'></span>");
                
                return nRow;
            },
              "aoColumns": [
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": true, sClass: "-"},
                {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": true, sClass: "-"},
                {"mDataProp": "kegiatan.namaKegiatan", "bSortable": false, sClass: "-", sDefaultContent: "-"},
                {"mDataProp": "bast.noBast", "bSortable": false, sClass: "-"},
                {"mDataProp": "kontrak.noKontrak", "bSortable": false, sClass: "-"},
                {"mDataProp": "rekanan.namaRekanan", "bSortable": false, sClass: "-"},
                {"mDataProp": "kegiatan.idKegiatan", "bSortable": true, sClass: "-"}

            ]
           
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}