$(document).ready(function() {
  
   gridsetor();
   
});

function getbanyaksetorrinci() {
    $.getJSON(getbasepath() + "/setor/json/getlistsetorrincibanyakbiaya", {idSetor: $("#idSetor").val(), tahunAnggaran: $("#tahun").val(), idspj: $("#idspj").val()},
    function(result) {
        $('#banyakrinci').val(result);
        //bootbox.alert("banyakrinci = "+ $('#banyakrinci').val()+"idSetor = "+ $('#idSetor').val());
    });
   
}

function gridsetor() {
    var urljson = getbasepath() + "/setor/json/getforsetorrincibiaya";
    
 
    if (typeof myTable == 'undefined') {
        myTable = $('#forrinci').dataTable({
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
                {name: "idSetor", value: $('#idSetor').val()},
                {name: "tahunAnggaran", value: $('#tahun').val()}
                
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
                var isceked = aData['nilaiSetor'] > 0 ? 'checked' : '';
                var edit = "<a href='" + getbasepath() + "/spptu/edispptu/" + aData['id'] + "' class='icon-edit' ></a> - <a href='" + getbasepath() + "/spptu/delspptu/" + aData['id'] + "' class='icon-remove' ></a>";
                // $('td:eq(0)', nRow).html(index);
                var inputcek = "<input type='checkbox' class='cekpilih' value='" + aData['idBl'] + "' name='cekpilih" + index + "' id='cekpilih" + index + "' onchange=enablerow(this," + aData['idBl'] + ") " + isceked + " />";

                var nilaianggaran = accounting.formatNumber(aData['nilaiAnggaran']);
                var inputnilaianggaran = "<input type='text' id='nilaianggaran" + aData['idBl'] + "' class='inputmoney' name='nilaianggaran" + aData['idBl'] + "' value='" + nilaianggaran + "' readOnly='true' >";
                
                var nilaisetor = accounting.formatNumber(aData['nilaiSetor']);
                var inputnilaisetor = "<input type='text' id='nilaisetor" + aData['idBl'] + "' class='inputmoney' name='nilaisetor" + aData['idBl'] + "' value='" + nilaisetor + "'  readOnly='true' >";
                //alert(inputnilaisetor);
                var idbl = "<input type='hidden' value='" + aData['idBl'] + "' id='idBl" + aData['idBl'] + "' name='idBl"+ aData['idBl'] +"' >";
                //var idkegiatan = "<input type='hidden' value='" + aData['idKegiatan'] + "' id='idKegiatan" + aData['idBl'] + "' >";
                var idbas = "<input type='hidden' value='" + aData['idAkun'] + "' id='idAkun" + aData['idBl'] + "' name='idAkun"+aData['idBl']+"' >";
                var idsetorrinci = "<input type='hidden' value='" + aData['idSetorrinci'] + "' id='idSetorrinci" + aData['idBl'] + "' name='idSetorrinci"+ aData['idBl']+"' >";

                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['nilaiAnggaran']));
                $('td:eq(2)', nRow).html(inputnilaianggaran);
                $('td:eq(3)', nRow).html(inputnilaisetor);
                $('td:eq(4)', nRow).html(inputcek + idbl + idbas + idsetorrinci);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaAkun", "bSortable": true, sDefaultContent: "-", sClass: "center"},
                {"mDataProp": "nilaiAnggaran", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "nilaiSetor", "bSortable": true, sDefaultContent: "-", sClass: "kanan"},
                {"mDataProp": "nilaiSetor", "bSortable": true, sDefaultContent: "-", sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
    
    getbanyaksetorrinci();
          
}

function enablerow(obj, idbl) {
    var param = "readonly";
    if ($(obj).is(':checked')) {
        param = false;
    }
    setdisabled(param, idbl)
}
function setdisabled(param, idbl) {
    $("#nilaisetor" + idbl).attr("readonly", param);
  //  $("#sisaspj" + idbl).attr("readonly", param);
    if (param == false) {
   //     var nilaisetor = accounting.unformat($("#nilaisetor" + idbl).val(), ",");
       // var nilaianggaran = accounting.unformat($("#nilaianggaran" + idbl).val(), ",");
      //  var nilaiisian = accounting.formatNumber((nilaisetor == 0 ? nilaianggaran : nilaisetor), 2, '.', ",")
      //  var nilaiisian = accounting.formatNumber((nilaisetor == 0 ), 2, '.', ",")
        $("#nilaisetor" + idbl).val(nilaiisian)
    }
   
}

