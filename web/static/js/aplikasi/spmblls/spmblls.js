$(document).ready(function() {
    gridsppup();
    //alert("MASSUK");
});


function gridsppup() {
    var urljson = getbasepath() + "/spmblls/json/getlistspmblls";
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
                {name: "namaskpd", value: $('#skpd').val()},
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
                var batas = aData['batas'];
                var d = new Date();
                var tgl = d.getUTCDate();
                var bln = d.getMonth() + 1;
                
                console.log("batas = "+batas);
                console.log("tgl = "+tgl);
                console.log("bln = " + bln);
                
                var edit = "<a href='" + getbasepath() + "/spmblls/editspmblls/" + aData['id'] + "'  title='Klik disini untuk melakukan entry/update SPM' class='icon-edit' ></a>";
                var editPFK = "<a href='" + getbasepath() + "/spmblls/editspmbllsPFK/" + aData['id'] + "'  title='Klik disini untuk melakukan entry/update SPM' class='icon-edit' ></a>";
                var potongannonayat = " - ";
                var potonganUangMuka = " - ";
                var potongannihil = " - ";
                var cekPFK = " - ";

                if (tgl > batas && bln == 12) { // tambahkan validasi bulan, edit 19 jan 16 by zainab
                    //console.log("masuk batas = ");
                    potongannonayat = " - ";
                    potonganUangMuka = " - ";
                    potongannihil = " - ";
                } else {
                   // console.log("masuk else = ");
                    if (aData['potongan'] == true) {
                        potongannonayat = "<a href='" + getbasepath() + "/spmpotnonayat/indexspm/" + aData['idspm'] + "'  title='Klik disini untuk melakukan entry/update Potongan SPM' class='icon-edit' ></a>";
                    }
                    if (aData['potonganUangMuka'] == true) {
                        potonganUangMuka = "<a href='" + getbasepath() + "/spmpotuangmuka/indexspm/" + aData['idspm'] + "'  title='Klik disini untuk melakukan entry/update Potongan SPM' class='icon-edit' ></a>";
                    }
                    if (aData['potonganNihil'] == true) {
                        potongannihil = "<a href='" + getbasepath() + "/spmpotnihil/indexspm/" + aData['idspm'] + "'  title='Klik disini untuk melakukan entry/update Potongan SPM' class='icon-edit' ></a>";
                    }
                }

                var batal = "";
                if (aData['idspm']) {
                    batal = "<a title='Klik disini untuk membatalkan spm' class='icon-remove linkpilihan' href='#' onclick=hapusspm(" + aData['idspm'] + ",'" + aData['noSpm'] + "') ></a>";

                }
                var namakegiatan = "<textarea style='border: none;width: 99%'>" + aData['namaKegiatan'] + "</textarea>"
                $('td:eq(4)', nRow).html(namakegiatan);
                $('td:eq(0)', nRow).html(index);
                $('td:eq(2)', nRow).html(accounting.formatNumber(aData['nilaiSpp']));
                $('td:eq(5)', nRow).html(edit + "    " + batal);
                $('td:eq(6)', nRow).html(potongannonayat);
                $('td:eq(7)', nRow).html(potonganUangMuka);
                $('td:eq(8)', nRow).html(potongannihil);
                $('td:eq(9)', nRow).html(editPFK);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "5%"},
                {"mDataProp": "noSpp", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "8%"},
                {"mDataProp": "id", "bSortable": false, sClass: "kanan", sDefaultContent: "-", "sWidth": "10%"},
                {"mDataProp": "noSpm", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "10%"},
                {"mDataProp": "namaKegiatan", "bSortable": false, sDefaultContent: "-", "sWidth": "30%"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "10%"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "5%"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "5%"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "5%"},
                {"mDataProp": "id", "bSortable": false, sClass: "center", sDefaultContent: "-", "sWidth": "5%"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}
function pindahhalamanadd() {
    var idskpd = $.trim($("#idskpd").val());
    if (idskpd) {
        window.location.replace(getbasepath() + "/spmblls/addspppup/" + idskpd);
    } else {
        window.location.replace(getbasepath() + "/spmblls/addspppup");
    }

}

function hapusspm(idspm, nospm) {
    var urlhapusspd = getbasepath() + "/spmup/json/hapusdataspm";
    bootbox.confirm("Anda akan membatalkan  data SPM dengan nomor " + nospm + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
            return   $.ajax({
                type: "POST",
                url: urlhapusspd,
                contentType: "text/plain; charset=utf-8",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({"idspm": idspm, "nospm": nospm})
            }).always(function(data) {
                gridsppup();
                bootbox.alert(data.responseText);
            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });


}