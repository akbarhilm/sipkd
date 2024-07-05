<%-- 
    Document   : indextmdpa
    Created on : Nov 11, 2014, 10:55:38 AM
    Author     : Xalamaster
--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">Home</a> 
        <span class="icon-angle-right"></span>
    </li>   
    <li><a href="#">TMDPA</a></li>
</ul>


<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Update TMDPA</div>
        <div class="actions">

        </div>
    </div>
    <div class="portlet-body flip-scroll">
    
        <form:form  method="post" commandName="cmdTmdpa"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/refftmdpa/updatetmdpa" class="form-horizontal">

            <div class="form-group">
                <label class="col-md-3 control-label">Tahun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <!--input type="text" name="pagupen" id="pagupen" readonly="true" size="4" value="${tahunAnggaran}"  class="m-wrap medium inputnumber"  /-->

                        <!--form:input id="tahunAnggaran" size="4"-->
                        <input type="hidden" id="idskpd" name="idskpd" value="${test}"  />
                        <form:input id="tahunAnggaran" path="idDpa" size="5"  class="m-wrap medium inputnumber" readonly="true"  />
                        <!--form:hidden id="idskpd" path="idSkpd" /-->
                        <form:hidden id="idTmdpa" path="idTmdpa" value="${datatmdpa[0]['idTmdpa']}" onChange="setformben(this.value)" />
                        <form:hidden id="skpdLevel" path="skpdLevel" value="${datatmdpa[0]['skpdLevel']}" />
                        <form:hidden id="idPendapatan" path="idPendapatan" value="${datatmdpa[0]['idPendapatan']}" />
                        <form:hidden id="idskpd" path="idSkpd"  value="${skpd.idSkpd}" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <!--input type="hidden" id="idskpd" onchange="getlistspdbtl(this.value)" name="idskpd" value="${idskpd}"  /--> 
                    <!--input type="text" readonly="true"  name="skpd"  id="skpd"  class="m-wrap large" size="40"  value="${namaskpd}" /-->
                    <!--form:input path="skpd" id="skpd" name="skpd"  class="m-wrap large" size="40" readonly="true" onchange="getlistspdbtl(this.value)" /-->
                    <form:input path="skpd" id="skpd" name="skpd"  value="${skpd.namaSkpd}" class="m-wrap large" size="40" readonly="true" />
                    <c:if test="${isall == 1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn dark" href="${pageContext.request.contextPath}/tmdpa/skpdlistpopup?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a></c:if>

                </div>
            </div>
           
            <div id="paguholder">
            <div class="form-group">
                <label class="col-md-3 control-label">PAGU Pendapatan:

                </label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="paguDpt" path="paguDpt" value="" size="30"  class="m-wrap medium inputnumber" readonly="true"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">PAGU BTL:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="paguBtl" path="paguBtl" value="" size="30"  class="m-wrap medium inputnumber" readonly="true"  /> 
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">PAGU BL:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="paguBl" path="paguBl" value="" size="30"  class="m-wrap medium inputnumber" readonly="true"  />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">PAGU Biaya:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="paguBiaya" path="paguBiaya" value="" size="30"  class="m-wrap medium inputnumber" readonly="true"  />
                    </div>
                </div>
            </div>
            </div>
            <div id="paholder">
            <div class="form-group">
                <label class="col-md-3 control-label">NRK / NIP PA:

                </label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="nrkPa" path="nrkPA" value="${datatmdpa[0]['nrkPA']}" size="8"  class="m-wrap large" maxlength="6" minlength="6" number="true" /> <form:errors path="nrkPA" class="error" />
                        / 
                        <form:input id="nipPa" path="nipPA" value="${datatmdpa[0]['nipPA']}" size="20" class="m-wrap large" maxlength="18" minlength="18" number="true"  /> <form:errors path="nipPA" class="error" />
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama PA:

                </label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="namaPa" path="namaPA" value="${datatmdpa[0]['namaPA']}" size="35" maxlength="50"  class="m-wrap large"  />
                    </div>
                </div>
            </div>
            </div>
            <br><br>
            <div id="dpaholder">
            <div class="form-group">
                <label class="col-md-3 control-label">No.DPA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="noDpa" path="noDpa" value="${datatmdpa[0]['noDpa']}" size="30"  class="m-wrap medium" readonly="true"  /> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tgl.DPA:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="tglDpa" path="tglDpa" value="${datatmdpa[0]['tglDpa']}" class="form-control form-control-inline input-small date-picker entrytanggal valid" size="14" readonly="true"   /> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">No.DPA Perubahan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="noDpaPrbh" path="noDpaPrbh" value="${datatmdpa[0]['noDpaPrbh']}" size="30"  class="m-wrap medium" readonly="true"  /> 
                    </div>
                </div>

            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Tgl.DPA Perubahan: </label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input id="tglDpaPrbh" path="tglDpaPrbh" value="${datatmdpa[0]['tglDpaPrbh']}" class="form-control form-control-inline input-small date-picker entrytanggal valid" readonly="true" size="14"   /> 
                    </div>
                </div>
            </div>
            </div>
            <div id="bendaharaholder">
                <div id="bendaharalist">
                </div>
            </div>
            <div id="bendaharaholder">

                
                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PKBLJ: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkBenPg" path="nrkBenPg"  value="${datatmdpa[0]['nrkBenPg']}" size="8"   class="m-wrap large" maxlength="6"  minlength="6" number="true"  />
                                 / 
                                <form:input path="nipBenPg" id='nipBenPg' value="${datatmdpa[0]['nipBenPg']}" size='20' maxlength="18"  minlength="18" number="true"   class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PKBLJ:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaBenPg" value="${datatmdpa[0]['namaBenPg']}" maxlength="50" path="namaBenPg" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>
                    <div id="bendaharalistskpd3"><br><br>
                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PKBANTUAN: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkBenPgBantuan" path="nrkBenPgBantuan" value="${datatmdpa[0]['nrkBenPgBantuan']}" size="8"  maxlength="6"  minlength="6" number="true"  class="m-wrap large"  />
                                 / 
                                 <form:input path="nipBenPgBantuan" id='nipBenPgBantuan' value="${datatmdpa[0]['nipBenPgBantuan']}" size='20'  maxlength="18"  minlength="18" number="true"  class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PKBANTUAN:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaBenPgBantuan" path="namaBenPgBantuan" maxlength="50" value="${datatmdpa[0]['namaBenPgBantuan']}" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PKBTT: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkBenPgBtt" path="nrkBenPgBTT" value="${datatmdpa[0]['nrkBenPgBTT']}" size="8"  maxlength="6"  minlength="6" number="true"  class="m-wrap large"  />
                                 / 
                                 <form:input path="nipBenPgBTT" id='nipBenPgBtt' value="${datatmdpa[0]['nipBenPgBTT']}" size='20'  maxlength="18"  minlength="18" number="true"  class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PKBTT:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaBenPgBtt" path="namaBenPgBTT" maxlength="50" value="${datatmdpa[0]['namaBenPgBTT']}" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PKBIAYA: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkBenPgPembiayaan" path="nrkBenPgPembiayaan" value="${datatmdpa[0]['nrkBenPgPembiayaan']}" size="8"  maxlength="6"  class="m-wrap large"  />
                                 / 
                                 <form:input path="nipBenPgPembiayaan" id='nipBenPgPembiayaan' value="${datatmdpa[0]['nipBenPgPembiayaan']}" size='20'  maxlength="18"  minlength="18" number="true"  class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PKBIAYA:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaBenPgPembiayaan" path="namaBenPgPembiayaan" maxlength="50" value="${datatmdpa[0]['namaBenPgPembiayaan']}" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>
                    </div>
                    <br>        
                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PPKBLJ: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkVerifikatorPg" path="nrkVerifikatorPg" value="${datatmdpa[0]['nrkVerifikatorPg']}" size="8"  maxlength="6" minlength="6" number="true"   class="m-wrap large"  />
                                / 
                                <form:input path="nipVerifikatorPg" id='nipVerifikatorPg' value="${datatmdpa[0]['nipVerifikatorPg']}" size='20'  maxlength="18" minlength="18" number="true"   class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PPKBLJ:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaVerifikatorPg" path="namaVerifikatorPg" maxlength="50" value="${datatmdpa[0]['namaVerifikatorPg']}" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>
                

                <div id="bendaharalistcpen1">   <br><br>    
                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PKDPT: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkBenPn" path="nrkBenPn" size="8" value="${datatmdpa[0]['nrkBenPn']}"   class="m-wrap large"  maxlength="6"  minlength="6" number="true"  />
                                / <form:input path="nipBenPn" id='nipBenPn' size='20' value="${datatmdpa[0]['nipBenPn']}"  maxlength="18"  class="m-wrap large" minlength="18" number="true"  />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PKDPT:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaBenPn" path="namaBenPn" value="${datatmdpa[0]['namaBenPn']}" maxlength="50" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>
                    <br>        
                    <div class="form-group">
                        <label class="col-md-3 control-label">NRK / NIP PPKDPT: </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="nrkVerifikatorPn" path="nrkVerifikatorPn" value="${datatmdpa[0]['nrkVerifikatorPn']}" size="8" maxlength="6"  minlength="6" number="true"   class="m-wrap large"  />
                                / 
                                <form:input path="nipVerifikatorPn" id='nipVerifikatorPn' value="${datatmdpa[0]['nipVerifikatorPn']}" size='20' maxlength="18" minlength="18" number="true"    class="m-wrap large" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Nama PPKDPT:

                        </label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <form:input id="namaVerifikatorPn" path="namaVerifikatorPn" maxlength="50" value="${datatmdpa[0]['namaVerifikatorPn']}" size="35"  class="m-wrap large"  />
                            </div>
                        </div>
                    </div>
                </div>

            </div>



            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk"   type="submit" class="btn blue" > Ubah </button>
                    <a class="btn blue"  href="${pageContext.request.contextPath}/refftmdpa" >Kembali</a>
                </div>
            </div>
            </script>
        </form:form>
    </div>
</div>  
<!--script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/referensi/edittmdpa.js"></script-->  
<script type="text/javascript">
    $(document).ready(function() {
        
      $("#bendaharalistskpd3").hide();
      $("#bendaharalistcpen1").hide();
        <c:if test="${datatmdpa[0]['skpdLevel'] == 3}" >
            $("#bendaharalistskpd3").show();
        </c:if>
        <c:if test="${datatmdpa[0]['idPendapatan'] == 1}" >
            $("#bendaharalistcpen1").show();
        </c:if>
        //alert('sini');
        tahunanggaran();
<c:if test="${isall != 1}"  >
        setpagu();
</c:if>
        
        $(".fancybox").fancybox({
            fitToView: true,
            autoSize: true,
            closeClick: true,
            openEffect: 'swing',
            closeEffect: 'swing',
            headers: {'X-fancyBox': true}
        });
    });
    function tahunanggaran() {
        $("#tahunAnggaran").val(${tahunAnggaran});

    }
<c:if test="${isall != 1}"  >
    function setpagu() {
        $('#paguDpt').val(accounting.formatNumber(${datatmdpa[0]['paguDpt']}));
        $('#paguBtl').val(accounting.formatNumber(${datatmdpa[0]['paguBtl']}));
        $('#paguBl').val(accounting.formatNumber(${datatmdpa[0]['paguBl']}));
        $('#paguBiaya').val(accounting.formatNumber(${datatmdpa[0]['paguBiaya']}));
        
        
    }
</c:if>
<c:if test="${isall == 1}"  >
    function setformben(idskpd) {
        $("#bendaharalistskpd3").hide();
        $("#bendaharalistcpen1").hide();
        $("#dpaholder").show();
        $("#paholder").show();
        $("#paguholder").show();
        console.log('sini ' + $("#idPendapatan").val() + " " + $("#skpdLevel").val());
        if ($("#idPendapatan").val() == '1') {
            console.log('masuk IF 1');
            $("#bendaharalistcpen1").show();
        }
        
        if ($("#skpdLevel").val() == '3') {
            console.log('masuk IF 3');
            $("#bendaharalistskpd3").show();
        }

    }
</c:if>



</script>