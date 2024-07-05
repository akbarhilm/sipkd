
$(document).ready(function() {
    var ta = (parseInt($('#tahun').val()) - 1);
    $('#tahunAngg').val(ta);

    if ($('#tahun').val() == "2018") {
        getIdskpdLama();
    }

    var uraian = "Pengembalian Sisa Belanja Langsung Tahun Lalu (" + ta + ")";
    $('#keterangan').val(uraian);
});

function getIdskpdLama() {
    $.getJSON(getbasepath() + "/setorjukor/json/getIdskpdLama", {idskpd: $("#idskpd").val(), tahunAnggaran: $("#tahunAngg").val()},
    function(result) {
        $('#idskpdlama').val(result);
    });
}

function getbanyaksetorrinci() {
    var idskpdval;

    if ($('#tahun').val() == "2018") {
        idskpdval = $('#idskpdlama').val();
    } else {
        idskpdval = $('#idskpd').val();
    }

    $.getJSON(getbasepath() + "/setorjukor/json/getbanyakstsblls", {idskpd: idskpdval, idkegiatan: $("#idkegiatan").val(), tahun: $("#tahunAngg").val(), nojurnal: $("#noJurnal").val()},
    function(result) {
        $('#banyakrinci').val(result);
        // bootbox.alert("banyakrinci = "+ $('#banyakrinci').val());
    });
}

function gridsetor() {
    var idskpdval;
    
    if ($('#tahun').val() == "2018") {
        idskpdval = $('#idskpdlama').val();
    } else {
        idskpdval = $('#idskpd').val();
    }

    var urljson = getbasepath() + "/setorjukor/json/getListAkunJukor";
    if (typeof myTable2 == 'undefined') {
        myTable2 = $('#forrinci2016').dataTable({
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
                formatnumberonkeyup();
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: idskpdval},
                {name: "idkegiatan", value: $('#idkegiatan').val()},
                {name: "tahun", value: $('#tahunAngg').val()},
                {name: "nojurnal", value: $('#noJurnal').val()}
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

                var inputnilaisetor = "<input type='text' id='nilaisetor" + index + "' name='nilaisetor" + index + "'  class='inputmoney' value='" + aData['nilaiSetor'] + "'  readOnly='true' />";
                
                //idAkun, akun, namaAkun, nilaiSetor, idKegiatan
                var idkegiatan = "<input type='hidden' value='" + aData['idKegiatan'] + "' id='idKegiatan" + index + "' name='idKegiatan" + index + "' >";
                var idbas = "<input type='hidden' value='" + aData['idAkun'] + "' id='idAkun" + index + "' name='idAkun" + index + "' >";

                $('td:eq(2)', nRow).html(inputnilaisetor + idkegiatan + idbas);

                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "akun", "bSortable": false, sClass: "center"},
                {"mDataProp": "namaAkun", "bSortable": true, sDefaultContent: "-", sClass: "left"},
                {"mDataProp": "nilaiSetor", "bSortable": true, sDefaultContent: "-", sClass: "kanan"}
            ]
        });

    }
    else
    {
        myTable2.fnClearTable(0);
        myTable2.fnDraw();
    }
    getbanyaksetorrinci();

}

function setKegiatan() {
    
    $('#noJurnal').val("");
    $('#kegiatan').val("");
    $('#idkegiatan').val("");
    $('#banyakrinci').val(0);
    
    clearrow();
}

function clearrow() {
    var i;
    var table = document.getElementById('tablebody2');
    var rows = table.rows;
    var jum = rows.length;

    for (i = 0; i < jum; i++) {
        table.deleteRow(0); // dihapus baris ke-0 sampai jumlahnya habis
    }
}
