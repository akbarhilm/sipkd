
 $(document).ready(function() {
 //gridspdbtlvalidasilist();
 
 });
 
function gridspdbtlvalidasilist() {
    var baseurl = getbasepath()
    var urljson = baseurl + "/spd/pembatalanspdrev/json/getlistspdvalidasi";

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
            "sAjaxSource": urljson,
            "aaSorting": [[3, "desc"]],
            "fnServerParams": function(aoData) {
                aoData.push(
                    {name: "tahunAnggaran", value: $('#tahun').val()},
                    {name: "id", value: $('#idskpd').val()}
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
                var nospd = "<input type='hidden'  id='nospd" + aData['idSpdSah'] + "'  name='nospd" + aData['idSpdSah'] + "' value='" + aData['noSpd'] + "'  />"
                var inputhiddennilaispd = "<input type='hidden'  name='nilaispd" + aData['IDSPD'] + "'   id='nilaispd" + aData['IDSPD'] + "' value='"+aData['NILAISPD']+"'  />";
                var jns = "<input type='hidden'  name='jenisspd" + aData['idSpdSah'] + "'   id='jenisspd" + aData['idSpdSah'] + "' value='" + aData['keterangan'] + "'  />";
                $('td:eq(1)', nRow).html(aData['noSpd'] + nospd+inputhiddennilaispd + jns);
                $('td:eq(4)', nRow).html(accounting.formatNumber(aData['v_spd']));
                $('td:eq(6)', nRow).html(aData['tglSpdSah'] != null && aData['tglSpdSah'] != '' && aData['tglSpdSah'] != 'undefined' ? moment.utc(aData['tglSpdSah'], 'SSS').format('DD-MM-YYYY') : '-');
                var pilih="<a class='icon-remove' href='"+getbasepath()+"/spd/pembatalanspdrev/addbatalrev/"+aData['idSpdSah']+ "/"+ aData['keterangan'] + "'  title='Klik disini untuk melakukan pembatalan SPD'></a>";
               // alert(jns);
                $('td:eq(7)', nRow).html(pilih);
                $('td:eq(3)', nRow).html(getTanggal(aData['tglSpd']),"dd-MM-yyyy");
                $('td:eq(6)', nRow).html(getTanggal(aData['tglSpdSah']),"dd-MM-yyyy");
                 
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idSpdSah", "bSortable": false, sClass: "center"},
                {"mDataProp": "noSpd", "bSortable": true, sClass: "center"},
                {"mDataProp": "keterangan", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglSpd", "bSortable": true, sClass: "center", sDefaultContent: "-"},
                {"mDataProp": "v_spd", "bSortable": true, sClass: "right", sDefaultContent: "-"},
                {"mDataProp": "noDokSpd", "bSortable": true, sClass: "center"},
                {"mDataProp": "tglSpdSah", "bSortable": true, sClass: "center"},
                {"mDataProp": "idSpdSah", "bSortable": false, sClass: "false", sClass: "center"}
            ]
        });
        //$("div.top").html("<button id='prosespengesahan' onclick=submitvalidasi() class='btn .dark-stripe' style='float: right'>Proses Pembatalan</button>");
    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }

}


function submitvalidasi( ) {
    var baseurl = getbasepath();
    var selected = [];
    var selectednoskpd = [];
     var arrnospd = [];
    $('.checkbox:checked').each(function() {
        var idspd = $(this).val();
        var nilai =     accounting.unformat($("#nilaispd"+idspd).val(), ",");  
        selected.push({"idspd":idspd,"nilaispd":nilai });
        var nospd = $("#nospd" + idspd).val();
        selectednoskpd.push(idspd);
        arrnospd.push(nospd);
        //alert(selectednoskpd.push(nospd));
    });


    bootbox.confirm("Anda akan melakukan pengesahan untuk SPD dengan nomor " + arrnospd.join(",") + " di SKPD " + $("#skpd").val() + ",  lanjutkan ? ", function(result) {
        if (result) {
            return      $.ajax({
                type: "POST",
                url: baseurl + "/spd/pembatalanspdrev/json/submitvalidasi",
                contentType: "text/plain; charset=utf-8",
                dataType: "json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(selected),
                beforeSend: function() {
                    console.log(" before submit " + new Date())

                }
            }).always(function(data) {
                gridspdbtlvalidasilist();
                bootbox.alert(data.responseText);
                //--print
                selectednoskpd.forEach(function(idspd) {
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
    var url = getbasepath() + "/spd/pembatalanspdrev/printvalidasi/cetakpengesahan/" + idspd;
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
