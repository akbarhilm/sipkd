$(document).ready(function() {
   settgl(); 
   grid();
            $(".fancybox").fancybox({
            fitToView: true,
            autoSize: true,
            closeClick: true,
            openEffect: 'swing',
            closeEffect: 'swing',
            headers: {'X-fancyBox': true}
        });
});

function toval(datake){
    var nilaidata = accounting.unformat($('#isi' + datake).val(), ",");
    $('#isi' + datake).val(accounting.formatNumber($(nilaidata).val()));
}


function grid( ) {
    var urljson = getbasepath()+"/spmpotayat/json/listpotayatjson?nospm="+$("#nospm").val();
    $("#btlspdtablebody").show();
    if (typeof myTable == 'undefined') {
        myTable = $('#btlspdtable').dataTable({
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
            "fnDrawCallback": function () {
                //$("#spprincitable :input").not("input[type='checkbox']").attr("readonly", "readonly");
                $(".inputmoney").priceFormat({prefix: "", suffix: "", centsSeparator: ",", thousandsSeparator: ".", limit: 15});
            },
            "fnServerParams": function(aoData) {
                aoData.push(
                        {name: "spm", value: $('#spm').val()}
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
                var nilaipot = accounting.formatNumber(aData['nilaiPot'], 2, '.', ",");
                var cpot = "<input type='hidden' name='cpot" + aData['idPot'] + "' id='cpot" + aData['idPot'] + "' value='" + aData['cPot'] + "'>";
                var status = "<input type='hidden' name='statuss" + aData['idPot'] + "' id='statuss" + aData['idPot'] + "' value='" + aData['status'] + "'>";
                $('td:eq(0)', nRow).html(index);
                //$('td:eq(2)', nRow).html(nilaipot);
                $('td:eq(2)', nRow).html(cpot+status+"<input type='text' name='isi" + aData['idPot'] + "' id='isi" + aData['idPot'] + "' value='" + nilaipot + "'  class='inputmoney'>");
                return nRow;
            },
            "aoColumns": [
                {"mDataProp": "idPot", "bSortable": false, sClass: "center"},
                {"mDataProp": "pot", "bSortable": false, sDefaultContent: "-"},
                {"mDataProp": "idPot", "bSortable": false, sClass: "center"}
            ]
        });

    }
    else
    {
        myTable.fnClearTable(0);
        myTable.fnDraw();
    }
}



function submitnilai( ) {
    var varurl = getbasepath() + "/spmpotayat/json/prosespindahsimpan";
    var dataac = [];
    var datapeg = {
            
            cpot1: $("#cpot1").val(),
            cpot2: $("#cpot2").val(),
            cpot3: $("#cpot3").val(),
            cpot4: $("#cpot4").val(),
            cpot5: $("#cpot5").val(),
            cpot6: $("#cpot6").val(),
            cpot7: $("#cpot7").val(),
            cpot8: $("#cpot8").val(),
            vpot1: $("#isi1").val(),
            vpot2: $("#isi2").val(),
            vpot3: $("#isi3").val(),
            vpot4: $("#isi4").val(),
            vpot5: $("#isi5").val(),
            vpot6: $("#isi6").val(),
            vpot7: $("#isi7").val(),
            vpot8: $("#isi8").val(),
            statuss1: $("#statuss1").val(),
            statuss2: $("#statuss2").val(),
            statuss3: $("#statuss3").val(),
            statuss4: $("#statuss4").val(),
            statuss5: $("#statuss5").val(),
            statuss6: $("#statuss6").val(),
            statuss7: $("#statuss7").val(),
            statuss8: $("#statuss8").val(),
            idspm: $("#nospm").val()
        }
        dataac=datapeg;
    
    return   $.ajax({
        type: "POST",
        url: varurl,
        contentType: "text/plain; charset=utf-8",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(dataac)
    }).always(function(data) {
        grid();
        bootbox.alert("Data SPM Potongan Ayat berhasil disimpan");
    });
}


function settgl(){
    var isi = $("#ssspm").val();
    var tahun = isi.substr(0,4);
    var bulan = isi.substr(5,2);
    var hari = isi.substr(8,2);
    var hasil = hari+"/"+bulan+"/"+tahun;
    
    $("#sispm").val(hasil);
       // alert("#sspm");
}