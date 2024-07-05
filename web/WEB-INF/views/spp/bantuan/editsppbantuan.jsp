<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script type="text/javascript">
    $(document).ready(function () {
        getDataBankDki();
        
    });
    
    function isNumber(evt) {
        evt = (evt) ? evt : window.event;
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
    
</script>

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
<form:form  method="post" commandName="refsppbantuan"  id="refsppbantuan" onsubmit="return cek()"  action="${pageContext.request.contextPath}/sppbantuan/prosesupdate" class="form-horizontal">
    <div class="portlet box red">
        <div class="portlet-title">
            <div class="caption"><i class="icon-cogs"></i>Form SPP BTL BANTUAN</div>       
        </div>


        <div class="portlet-body flip-scroll">
            <div class="col-md-7">
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
                    <label class="col-md-3 control-label">NIP/NAMA Bendahara:</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:input   path="nipBendahara" id="nipBendahara"  cssClass="required"   size="20" maxlength="18"   /> / <form:input   path="namaBendahara" id="namaBendahara"  cssClass="required"   size="45" maxlength="50"   />  
                            <form:errors path="nipBendahara"  cssClass="error"  /> 
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Kode Bank:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:hidden path="kodeBank" id='kodeBank'    />
                            <form:hidden path="idBank" id='idBank'    />
                            <form:input   path="kodeBankTransfer" id="kodeBankTransfer"  cssClass="required"   size="10" maxlength="7"  readonly="true" onchange="getDataBankDki()" />  &nbsp;<a  class="fancybox fancybox.iframe btn green" href="${pageContext.request.contextPath}/kontrak/listbankinduk?target='_top'" title="Pilih Bank"  ><i class="icon-zoom-in"></i> Pilih</a> 
                            <form:errors path="kodeBankTransfer"  cssClass="error"  />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-md-3 control-label">Nama Bank:</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:input   path="namaBankTransfer" id="namaBankTransfer"    size="45" maxlength="50"  readonly="true"  />  
                            <form:errors path="namaBankTransfer"  cssClass="error"  /> 
                        </div>
                    </div>
                </div>       
                <div class="form-group">
                    <label class="col-md-3 control-label">No. Rekening Bank:</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <!--ruleCekCIF111 onchange="getDataBankDki()" -->
                            <form:input   path="rekeningBank" id="rekeningBank"  cssClass="required " onchange=""  onkeypress="return isNumber(event)" size="45" maxlength="50"   />  
                            <form:errors path="rekeningBank"  cssClass="error"  /> 
                        </div>
                    </div>
                </div> 


                <div class="form-group">
                    <label class="col-md-3 control-label">Nama Penerima:</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <!--ruleCekNamaBantuan -->
                            <form:input   path="namaPenerima" id="namaPenerima"  cssClass="required "  size="50" maxlength="55"   />  
                            <form:errors path="namaPenerima"  cssClass="error"  /> 
                        </div>
                    </div>
                </div> 

                <div class="form-group">
                    <label class="col-md-3 control-label">Alamat Bantuan:</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:input   path="alamatBantuan" id="alamatBantuan"    size="75" maxlength="80"   />  
                            <form:errors path="alamatBantuan"  cssClass="error"  /> 
                        </div>
                    </div>
                </div> 

                <div class="form-group">
                    <label class="col-md-3 control-label">Nama Org / Yayasan:</label>
                    <div class="col-md-9">
                        <div class="input-group">
                            <form:input   path="namaYayasan" id="namaYayasan"    size="45" maxlength="50"   />  
                            <form:errors path="namaYayasan"  cssClass="error"  /> 
                        </div>
                    </div>
                </div>          


                <div class="form-group">
                    <label class="col-md-3 control-label">Alasan Pengajuan SPP-BANTUAN:</label>
                    <div class="col-md-4">
                        <div class="input-group">
                            <form:input   path="keterangan" id="uraian"   cssClass="required"   size="55" maxlength="100"   />  
                            <form:errors path="keterangan"  cssClass="error"  />
                        </div>
                    </div>
                </div>  

            </div>
            <div class="col-md-4 col-md-offset-1">

                <div class="portlet box red">
                    <div class="portlet-title ">

                        <div class="caption" style="font-size: 15px">Data Bank DKI</div>       

                    </div>
                    <div class="portlet-body flip-scroll">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-md-4 control-label">Kode Bank :</label>
                                <div class="col-md-5">
                                    <input name="dkikodebank" id="dkikodebank" type="text" readonly="true" />
                                </div>
                            </div>


                            <div class="form-group">
                                <label class="col-md-4 control-label">Nama Bank :</label>
                                <div class="col-md-5">
                                    <input name="dkinamabank" id="dkinamabank" type="text" readonly="true" size="31" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">No Rekening :</label>
                                <div class="col-md-5">
                                    <input name="dkinorek" id="dkinorek" type="text" readonly="true" size="31" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-4 control-label">Nama :</label>
                                <div class="col-md-5">
                                    <TEXTAREA name="dkinama" id="dkinama" cols="30" ROWS="3" readonly="true"></TEXTAREA>
                                    <!-- <input name="dkinama" id="dkinama" type="text" readonly="true" size="31" /> -->
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>

            <div class="portlet box ">

            </div>


            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                    <button id="buttoninduk" onkeyup="enableddetail()" type="submit" class="btn blue" >${not empty spdBTLMaster.idSpd && spdBTLMaster.idSpd > 0? 'Simpan':'Simpan'}</button>
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
                <label class="col-md-3 control-label">Nilai Sisa SPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="nilaiSpd" id="nilaiSpd" value="${nilaiSpd}" type="text"   size="25" maxlength="20"  readonly="true"   /> 
                    </div>
                </div>
            </div> 
            <div class="form-group">
                <label class="col-md-3 control-label">Nilai SPP:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input path="nilaiSpp" id="nilaiSpp" value="${nilaiSpp}" onblur="hitungnilaisppsisa(this.value)" type="text" size="25" maxlength="20"    /> 
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

