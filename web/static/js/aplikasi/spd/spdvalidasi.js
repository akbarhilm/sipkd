function getlistspdbtl(id) {
    $.getJSON(getbasepath() + "/common/json/getpagudansisa/" + id,
            function (result) {
                $('#pagubtl').val(accounting.formatNumber(result.pagu));
                $('#sisaspd').val(accounting.formatNumber(result.vspd));
            });
    gridspdbtlvalidasilist();
}



function gridspdbtlvalidasilist() {

    var urljson = getbasepath() + "/spd/pengajuancetak/json/getlistspdvalidasi";

    if (typeof dtabel == 'undefined') {
        dtabel = $('#btlspdtable').dataTable({
            "bPaginate": true,
            "sPaginationType": "bootstrap",
            "bJQueryUI": false,
            "bProcessing": true,
            "bServerSide": true,
            "bInfo": true,
            "bScrollCollapse": true,
            "bFilter": false,
            "sAjaxSource": urljson,
              "sDom": '<"top"i>lrt<"bottom"i>p<"clear">',
            "aaSorting": [[1, "asc"]],
            "fnServerParams": function (aoData) {
                aoData.push(
                        {name: "id", value: $('#idskpd').val()}
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
                $('td:eq(0)', nRow).html(index);
                var nospd = "<input type='hidden'  id='nospd" + aData['IDSPD'] + "'  name='nospd" + aData['IDSPD'] + "' value='" + aData['NOSPD'] + "'  />"
                var inputhiddennilaispd = "<input type='hidden'  name='nilaispd" + aData['IDSPD'] + "'   id='nilaispd" + aData['IDSPD'] + "' value='" + aData['NILAISPD'] + "'  />";
                var inputhiddentglcetak2 = "<input type='hidden'  name='tglcetak2" + aData['IDSPD'] + "'   id='tglcetak2" + aData['IDSPD'] + "' value='" + aData['TGLCETAK2'] + "'  />";
                $('td:eq(1)', nRow).html(aData['NOSPD'] + nospd + inputhiddennilaispd + inputhiddentglcetak2);
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['NILAISPD']));
                // $('td:eq(6)', nRow).html(aData['TGLCETAK'] != null && aData['TGLCETAK'] != '' && aData['TGLCETAK'] != 'undefined' ? moment.utc(aData['TGLCETAK'], 'SSS').format('DD-MM-YYYY') : '-');
                var namapeg = (aData['NAMAPEGAWAI'] != null && aData['NAMAPEGAWAI'] != '' && aData['NAMAPEGAWAI'] != 'undefined') ? aData['NAMAPEGAWAI'] : '-';
                var namajab = aData['NAMAJABATAN'] != null && aData['NAMAJABATAN'] != '' && aData['NAMAJABATAN'] != 'undefined' ? aData['NAMAJABATAN'] : '-';
                var buttonnamattd = aData['STATUS'] == 'PENGAJUAN' ? '<a  class="fancybox fancybox.iframe btn dark" href="' + getbasepath() + '/common/listpejabatppkd/' + aData['IDSPD'] + '/SPD/' + aData['NILAISPD'] + '?target=top" title="Pilih PejabatPPKD"  ><i class="icon-zoom-in"></i></a>' : " ";
                var inputhiddenidpejabat = "<input type='hidden'  name='idpejabatppkd" + aData['IDSPD'] + "'   id='idpejabatppkd" + aData['IDSPD'] + "'  />";
                var spannamapejabat = "<span id='identitaspejabat" + aData['IDSPD'] + "' ></span>";
                var cekprint = "-";

                if (aData['STATUS'] == 'CETAK') {
                    var cekprint = "<input type='radio' name='cek'  id='cek" + aData['IDSPD'] + "' value='" + aData['IDSPD'] + "' class='checkbox' />";

                }
                $('td:eq(8)', nRow).html(cekprint);
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "IDSPD", "bSortable": false, sClass: "center"},
                {"mDataProp": "NOSPD", "bSortable": true, sClass: "center"},
                {"mDataProp": "JENIS", "bSortable": true, sClass: "left"},
                {"mDataProp": "D_SPDNO", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "NILAISPD", "bSortable": true, sClass: "right", sDefaultContent: "-"},
                {"mDataProp": "STATUS", "bSortable": true, sClass: "left"},
                {"mDataProp": "TGLCETAK1", "bSortable": false, sClass: "center"},
                {"mDataProp": "NAMAPEGAWAI", "bSortable": true, sClass: "left", sDefaultContent: "-"},
                {"mDataProp": "IDSPD", "bSortable": false, sClass: "center"}
            ]
        });
        $("div.top").html("<button id='prosespengesahan' onclick=submitvalidasi() class='btn .dark-stripe' style='float: right'>Proses Pengesahan</button>");
    }
    else
    {
        dtabel.fnClearTable(0);
        dtabel.fnDraw();
    }

}


function submitvalidasi( ) {
    var baseurl = getbasepath();
    var selected = [];
    var selectednoskpd = [];
    var arrnospd = [];
    $('.checkbox:checked').each(function () {
        var idspd = $(this).val();
        var nilai = accounting.unformat($("#nilaispd" + idspd).val(), '.', ","); //accounting.unformat($("#nilaispd" + idspd).val(), ",");
        console.log("nilai spd unformat 2 = "+accounting.unformat($("#nilaispd" + idspd).val(), '.', ","));
        
        selected.push({"idspd": idspd, "nilaispd": nilai});
        var nospd = $("#nospd" + idspd).val();
        selectednoskpd.push(idspd);
        arrnospd.push(nospd);
        //alert(selectednoskpd.push(nospd));
    });


    bootbox.confirm("Anda akan melakukan pengesahan untuk SPD dengan nomor " + arrnospd.join(",") + " di " + $("#skpd").val() + ",  lanjutkan ? ", function (result) {
        if (result) {
            return      $.ajax({
                type: "POST",
                url: baseurl + "/spd/pengajuancetak/json/submitvalidasi",
                contentType: "text/plain; charset=utf-8",
                dataType: "json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(selected),
                beforeSend: function () {
                    console.log(" before submit " + new Date())

                }
            }).always(function (data) {
                gridspdbtlvalidasilist();
                bootbox.alert(data.responseText);
                //--print
                selectednoskpd.forEach(function (idspd) {
                    printUrl(idspd);
                });

            });
        } else {
            bootbox.hideAll();
            return result;
        }
    });

}

function printUrl(idspd) {
    var url = getbasepath() + "/spd/pengajuancetak/printvalidasi/cetakpengesahan/" + idspd;
    printPage(url)

}
function closePrint() {
    document.body.removeChild(this.__container__);
}

function setPrint() {
    this.contentWindow.__container__ = this;
    this.contentWindow.onbeforeunload = closePrint;
    this.contentWindow.onafterprint = closePrint;
    this.contentWindow.print();
}

function printPage(sURL) {
    var oHiddFrame = document.createElement("iframe");
    oHiddFrame.onload = setPrint;
    oHiddFrame.style.visibility = "hidden";
    oHiddFrame.style.position = "fixed";
    oHiddFrame.style.right = "0";
    oHiddFrame.style.bottom = "0";
    oHiddFrame.src = sURL;
    document.body.appendChild(oHiddFrame);
}
