<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="portlet box red">
    <div class="portlet-title">
        <div class="caption"><i class="icon-cogs"></i>Detail SP2D</div>       
    </div>
    <div class="portlet-body">
        <form:form  method="post" commandName="progcmd"  id="spdBTLMasterform"   action="${pageContext.request.contextPath}/bankrek/simpanbankrek" class="form-horizontal">
            <div class="form-group">
                <label class="col-md-3 control-label">ID SP2D:</label>
                <div class="col-md-4">
                    
                    <form:input path="idSp2d" id="idSp2d" cssClass="required ruleA04" type="text" size="10"  readonly='true' /> 
                   
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-3 control-label">NO SP2D:</label>
                <div class="col-md-5">
                    <div class="input-group">
                       
                        <form:input path="noSp2d" id="noSp2d"  cssClass="required"   type="text" size="10" readonly='true'  />
                        
                    </div>
                </div>
            </div>      

            <div class="form-group">
                <label class="col-md-3 control-label">NO DOKUMEN SP2D:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="noDokSp2d" id="noDokSp2d"    size="30" readonly='true' />  
                        
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">SKPD:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="skpd" id="skpd"    size="55" maxlength="50"  readonly='true' />  
                      
                    </div>
                </div>  
            </div>  
            <div class="form-group">
                <label class="col-md-3 control-label">NILAI SPP:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nilaiSpp" id="nilaiSpp"    size="20" readonly='true' />  
                        
                    </div>
                </div>  
            </div>     
            <div class="form-group">
                <label class="col-md-3 control-label">NILAI POTONGAN SPM:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nilaiPotSpm" id="nilaiPotSpm"    size="20" readonly='true' />  
                        
                    </div>
                </div>  
            </div>     

            <div class="form-group">
                <label class="col-md-3 control-label">NILAI SP2D:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="nilaiSp2d" id="nilaiSp2d"    size="20" readonly='true'  />  
                        
                    </div>
                </div>  
            </div>     
            <div class="form-group">
                <label class="col-md-3 control-label">KODE BANK:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodeBankTujuan" id="kodeBankTujuan"    size="5" readonly='true'  />  
                        
                    </div>
                </div>  
            </div>     
            
            <div class="form-group">
                <label class="col-md-3 control-label">REKENING TUJUAN:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="noRekTujuan" id="noRekTujuan"    size="20" readonly='true'   />  
                        
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">NAMA TUJUAN:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="namaTujuan" id="namaTujuan"    size="50" readonly='true'     />  
                        
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">BEBAN:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="kodeBeban" id="kodeBeban"    size="5" readonly='true'   />  
                       
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">KETERANGAN:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="ketSp2d" id="ketSp2d"    size="125" readonly='true'  />  
                        
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">WILAYAH:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="wilSp2d" id="wilSp2d"    size="3" readonly='true'     />  
                        
                    </div>
                </div>  
            </div>   
            <div class="form-group">
                <label class="col-md-3 control-label">TANGGAL SP2D:</label>
                <div class="col-md-4">
                    <div class="input-group">
                        <form:input   path="tglSp2d" id="tglSp2d"    size="20" readonly='true'     />  
                        
                    </div>
                </div>  
            </div>   

<!--            <div class="form-actions fluid">
                <div class="col-md-offset-3 col-md-9">
                   
                    <a class="btn blue"  href="${pageContext.request.contextPath}/bankrek" >Kembali</a>
                </div>
            </div>-->
        </form:form>
    </div>
</div> 