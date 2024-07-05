<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<ul class="breadcrumb">
    <li>
        <i class="icon-home"></i>
        <a href="${pageContext.request.contextPath}/beranda">SPP</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li>
        <a href="#" onclick="pindahhalamanadepan()">Daftar SPP BTL BANTUAN</a> 
        <span class="icon-angle-right"></span>
    </li>
    <li><a href="#">Ubah  SPP BTL BANTUAN</a></li>
</ul>
<div  ${pesan != null ?"class='alert alert-success'":""}   >${pesan}</div>
<form:form  method="post" commandName="refsppbantuan"  id="refsppbantuan"   action="${pageContext.request.contextPath}/sppbantuan/prosesupdate" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form SPP BTL BANTUAN</div>       
        </div>


        <div class="portlet-body flip-scroll">
            <div class="form-group">
                <label class="col-md-3 control-label">Tahun Anggaran:</label>
                <div class="col-md-4">
                    <form:hidden path="id" id='id'    />
                    <input type="hidden" id="isadd" name="isadd" value="${isadd}" />
                    <form:input path="tahun" id="tahun" type="text" size="6" maxlength="4" readonly='true'  value="${tahunAnggaran}" /> 
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Bulan:</label>
                <div class="col-md-4">
                    <form:input   path="bulan" id="bulan"   cssClass="required  entrybulan ruleCekBulan"     size="8"    />mm/yyyy
                    <form:errors path="bulan" class="label label-important" />
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-5">
                    <div class="input-group">
                        <form:hidden path="skpd.idSkpd" id='idskpd' readnoly="true"    />
                        <form:hidden path="idskpdkoor" id='idskpdkoor' readnoly="true"  value="${idskpdkoor}"  />
                        <form:input path="namaSkpdKoor" id="namaSkpdKoor"   type="text" size="55" maxlength="80" readonly='true' value="${namaSkpdKoor}"   />
                        <c:if test="${isall ==1}"  >  &nbsp; &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/common/listskpd?target='_top'" title="Pilih SKPD"  ><i class="icon-zoom-in"></i> Pilih</a> </c:if>
                        <form:errors path="skpd.idSkpd" class="label label-important" />
                    </div>
                </div>
            </div>           
            <div class="form-group">
                <label class="col-md-3 control-label">No SPP:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="noSpp" id="noSpp" type="text" size="25" maxlength="20" readonly='true'  /> 
                    </div>
                </div>
            </div> 

            <div class="form-group">
                <label class="col-md-3 control-label">NIP/NAMA PPTK:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:input   path="nipBendahara" id="nipBendahara"  cssClass="required"   size="20" maxlength="18"   /> / <form:input   path="namaBendahara" id="namaBendahara"  cssClass="required"   size="45" maxlength="50"   />  
                        <form:errors path="nipBendahara"  cssClass="error"  /> 
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">Nama Bank:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:input   path="namaBank" id="namaBank"    size="45" maxlength="50"   />  
                        <form:errors path="namaBank"  cssClass="error"  /> 
                    </div>
                </div>
            </div>       
            <div class="form-group">
                <label class="col-md-3 control-label">No. Rekening Bank:</label>
                <div class="col-md-9">
                    <div class="input-group">
                        <form:input   path="rekeningBank" id="rekeningBank"    size="45" maxlength="50"   />  
                        <form:errors path="rekeningBank"  cssClass="error"  /> 
                    </div>
                </div>
            </div>        

            <div class="form-group">
                <label class="col-md-3 control-label">Alasan Pengajuan SPP-BANTUAN:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="keterangan" id="uraian"   cssClass="required"   size="55" maxlength="70"   />  
                        <form:errors path="keterangan"  cssClass="error"  />
                    </div>
                </div>
            </div>  





            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onkeyup="enableddetail()" type="submit" class="btn blue" >${not empty spdBTLMaster.idSpd && spdBTLMaster.idSpd > 0? 'Ubah':'Ubah'}</button>
                    <a class="btn blue"  onclick="pindahhalamanadepan()" href="#">Kembali</a>
                </div>
            </div>

        </div>
    </div>



    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Rinci SPP BTL Bantuan</div>       
        </div>            
        <div class="portlet-body flip-scroll">

            <div class="form-group">
                <label class="col-md-3 control-label">No. SPD</label>
                <div class="col-md-4">
                    <div class="input-group">

                        <form:hidden path="idSpd" id='idSpd' readonly="true" value="${idspd}"  />
                        <form:hidden path="idBtlBantuan" id='idBtlBantuan' readonly="true" value="${idbtlbantuan}"  />
                        <form:input  path="noSpd" id="noSpd"  size="5"  readonly="true" value="${nospd}"  />  
                        <span id='noSpd'></span>
                        &nbsp;<a class="fancybox fancybox.iframe btn dark" id="popupspd" href="${pageContext.request.contextPath}/sppbantuan/listspdpopup/${idskpdkoor}?target='_top" title="Pilih SPD"><i class="icon-zoom-in"></i> Pilih</a>
                    </div>
                </div>  
            </div>      

            <div class="form-group">
                <label class="col-md-3 control-label">Nama Kegiatan:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="kegiatan.idKegiatan" id='idKegiatan'  value="${idkegiatan}"  />
                        <form:input path="kegiatan.namaKegiatan" id="namaKegiatan" value="${namakegiatan}" type="text" size="68"  readonly='true'   /> 
                    </div>
                </div>
            </div>    

            <div class="form-group">
                <label class="col-md-3 control-label">Akun:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:hidden path="akun.idAkun" id='idAkun' value="${idakun}"   />
                        <form:input path="akun.namaAkun" id="namaAkun" value="${namaakun}" type="text" size="68" readonly='true' />  
                    </div>
                </div>
            </div>     

            <div class="form-group">
                <label class="col-md-3 control-label">Nilai SPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="nilaiSpd" id="nilaiSpd" value="${nilaiSpd}" type="text" onchange="hitungnilaisppsisa()" size="25" maxlength="20"  readonly="true"   /> 
                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai SPP:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="nilaiSpp" id="nilaiSpp" value="${nilaiSpp}" onchange="hitungnilaisppsisa()" type="text" size="25" maxlength="20"    /> 
                    </div>
                </div>
            </div> 




            <div class="form-group">
                <label class="col-md-3 control-label">Sisa SPP:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <span id="nilaiSppSisaAkhir">${nilaiSppSisa}</span>
                        <form:hidden path="nilaiSppSisa" id="nilaiSppSisa"     /> 
                    </div>
                </div>
            </div> 





        </div>
    </div>


</form:form>
<script  type="text/javascript" src="${pageContext.request.contextPath}/static/js/aplikasi/sppbantuan/addsppbantuan.js"></script>  

