$(document).ready(function() {

    gridbapkas();

});



function gridbapkas() {

    var urljson = getbasepath() + "/bapkas/json/getlistbapkas";
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
            "aLengthMenu": [[25, 50, 100, 200, 5000], [25, 50, 100, 200, "All"]],
            "iDisplayLength": 25,
            "sAjaxSource": urljson,
            "aaSorting": [[2, "asc"]],
            "fnDrawCallback": function() {
                //   $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "idskpd", value: $('#idskpd').val()},
                {name: "tglBkuPros", value: $('#tglBkuPros').val()},
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
                var isceked = aData['nihil'] == '1' ? 'checked' : '';
                var stat = aData['statusBkuBa'];
                var tp = $('#tglBkuPros').val();
                var tahun = aData['tahun'];
                var tgl = aData['tglBkuBa'];
                var bln = aData['blnBkuBa'];
                var idb = aData['idSkpdBAPKas'];

                var is = aData['skpd']['idSkpd'];
                var thn = aData['tahun'];
                var kodwil = aData['kodeWilSp2d'];
                var idskpd = $.trim($("#idskpd").val());
                /*if (idskpd == is){
                 $('#tambahspj').attr("disabled", true);
                 }else
                 {
                 $('#tambahspj').attr("disabled", false);
                 }*/

                var edit = "-";
                var namafile = "Laporan-Berita_Acara_Pemeriksaan-Kas-" + thn + "-" + is + ".pdf";
                // alert("BKU Pros = "+tp+" ==> "+stat);
                if (tp == 0)
                {
                    console.log("cek anitaikan tp 0");
                    //edit = "<a href='" + getbasepath() + "/bapkas/editbapkas/" + is +"/"+aData['tahun']+ "/" +tgl+ "/" +bln+ "/" +idb+ "' class='icon-edit' ></a>  -  <a href='" + getbasepath() + "/bapkas/deletebapkas/"+ aData['skpd']['idSkpd'] +"/"+aData['tahun']+"/"+bln+ "' class='icon-remove' ></a>  -  <a href='" + getbasepath() + "/bapkas/cetakbapkas/"+aData['skpd']['idSkpd'] +"/"+aData['tahun']+ "/"+bln+ "/"+namafile+ "' class='icon-book' ></a>";
                    edit = "<a href='" + getbasepath() + "/bapkas/editbapkas/" + is + "/" + aData['tahun'] + "/" + tgl + "/" + bln + "/" + idb + "' class='icon-edit' ></a>  -  <a href='" + getbasepath() + "/bapkas/deletebapkas/" + aData['skpd']['idSkpd'] + "/" + aData['tahun'] + "/" + bln + "' class='icon-remove' ></a>  -  <a href='#' onclick='cetak(" + "" + aData['blnBkuBa'].toString() + ")' class='icon-book' ></a>";
                } else
                {
                    edit = "<a href='" + getbasepath() + "/bapkas/viewbapkas/" + aData['skpd']['idSkpd'] + "/" + aData['tahun'] + "/" + bln + "' class='icon-print' ></a>  -  <a href='#' onclick='cetak(" + "" + aData['blnBkuBa'].toString() + ")' class='icon-book' ></a>";
                }

                $('td:eq(0)', nRow).html(index);
                $('td:eq(1)', nRow).html(tgl);
                $('td:eq(2)', nRow).html(bln);
                $('td:eq(3)', nRow).html(kodwil);
                $('td:eq(4)', nRow).html(edit);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "tahun", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglBkuBa", "bSortable": true, sClass: "center"},
                {"mDataProp": "blnBkuBa", "bSortable": true, sClass: "center"},
                {"mDataProp": "kodeWilSp2d", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglBkuBa", "bSortable": true, sClass: "center"}

            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}


function pindahhalaman() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/bapkas/addbapkas/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/bapkas/addbapkas");
    }

}

function setbtntambah() {

    // var bulan = $('#idskpd').val();
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd == "") {
        $('#tambahspj').attr("hidden", true);
    } else
    {
        $('#tambahspj').attr("disabled", true);
    }
} 

function cetak(bulan) {
    var idskpd = $("#idskpd").val();
    var tahun = $("#tahun").val();
    var bln; 

    if (bulan.toString().length == 1) {
        bln = "0" + bulan.toString();
    } else {
        bln = bulan;
    }

    bulan.length 
    window.location.href = getbasepath() + "/bapkas/json/prosescetak?tahun=" + tahun + "&idskpd=" + idskpd + "&bulan=" + bln;
}
