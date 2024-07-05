$(document).ready(function() {
    grid();

});

function cekgrid() {
    var panjang = $("#nokontrak").val().length;
    if (panjang > 3 || panjang == 0) {
        grid();
    }
    /*
     $("#kodekegiatanfilter").keyup(function() {
     var panjang = $("#kodekegiatanfilter").val().length;
     if (panjang > 3) {
     grid();
     }
     });

     $("#namakegiatanfilter").keyup(function() {
     var panjang = $(this).val().length;
     if (panjang > 3) {
     grid();
     }
     });
     $("#nokontrak").keyup(function() {

     }); */
}

function ambilkontrak(id) {
    $('#idKontrak', window.parent.document).val($("#idKontrak" + id).val());
    $('#namaKegiatan', window.parent.document).val($("#namaKegiatan" + id).val());
    $('#nilaiBast', window.parent.document).val(accounting.formatNumber($("#nilaiBast" + id).val()));
    $('#nilaiKontrak', window.parent.document).val(accounting.formatNumber($("#nilaiKontrak" + id).val()));
    $('#popupakun', window.parent.document).attr('href', getbasepath() + "/bast/listpopupakun/" + $("#idkegiatan" + id).val())
    //$('#idspd', window.parent.document).val($("#idspd" + id).val()).change();
    //$('#idSpd', window.parent.document).val($("#idspd" + id).val()).change();
    $('#idkegiatan', window.parent.document).val($("#idkegiatan" + id).val()).change();
    parent.$.fancybox.close();


}
function grid() {
    var urljson = getbasepath() + "/bast/json/listpopupkontrak";
    $("#kontrakpopup").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#kontrakpopup').dataTable({
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
                        //  {name: "namaskpd", value: $('#skpd').val()},
                                // {name: "kodeskpd", value: $('#kodeskpd').val()},
                                        {name: "idskpd", value: $('#idskpd').val()},
                                {name: "tahun", value: $('#tahun').val()},
                                {name: "kodekegiatan", value: $('#kodekegiatanfilter').val()},
                                {name: "namakegiatan", value: $('#namakegiatanfilter').val()},
                                {name: "nokontrak", value: $('#nokontrak').val()}
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

                        var idKontrak = "<input type='hidden' id='idKontrak" + index + "' value='" + aData['kontrak']['idKontrak'] + "' />";
                        var idKegiatan = "<input type='hidden' id='idkegiatan" + index + "' value='" + aData['idkegiatan'] + "' />";
                        //var idspd = "<input type='hidden' id='idspd" + index + "' value='" + aData['kontrak']['idSpd'] + "' />";
                        //console.log( " id spd "+ aData['kontrak']);
                        var namaKegiatan = "<input type='hidden' id='namaKegiatan" + index + "' value='" + aData['kegiatan']['kodeKegiatan'] + " / " + aData['kegiatan']['namaKegiatan'] + "' />";
                        var nilaiBast = "<input type='hidden' id='nilaiBast" + index + "' value='" + aData['nilaiBast'] + "' />";

                        var ceklis = "<span class='icon-ok-sign linkpilihan' onclick='ambilkontrak(" + index + ")'></span>";
                        $('td:eq(5)', nRow).html(accounting.formatNumber(aData['nilaiBast']));
                        $('td:eq(6)', nRow).html(accounting.formatNumber(aData['nilaiKontrak']));
                        $('td:eq(0)', nRow).html(index);
                        $('td:eq(7)', nRow).html(idKontrak + idKegiatan + namaKegiatan + nilaiBast + ceklis);

                        return nRow;
                    },
                    "aoColumns": [
                        {"mDataProp": "kontrak.idKontrak", "bSortable": false, sClass: "-"},
                        {"mDataProp": "kegiatan.kodeKegiatan", "bSortable": true, sClass: "-"},
                        {"mDataProp": "kegiatan.namaKegiatan", "bSortable": true, sClass: "-", sDefaultContent: "-"},
                        {"mDataProp": "kontrak.noKontrak", "bSortable": false, sClass: "-"},
                        {"mDataProp": "skpd.namaSkpd", "bSortable": false, sClass: "-"},
                        {"mDataProp": "nilaiBast", "bSortable": false, sClass: "kanan"},
                        {"mDataProp": "nilaiKontrak", "bSortable": false, sClass: "kanan"},
                        {"mDataProp": "kontrak.idKontrak", "bSortable": false, sClass: "center"}

                    ]

                });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}


