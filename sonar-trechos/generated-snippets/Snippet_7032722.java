public static class BucketLifecycleConfigurationHandler extends AbstractHandler {

        private final BucketLifecycleConfiguration configuration =
                new BucketLifecycleConfiguration(new ArrayList<Rule>());

        private Rule currentRule;
        private Transition currentTransition;
        private NoncurrentVersionTransition currentNcvTransition;
        private AbortIncompleteMultipartUpload abortIncompleteMultipartUpload;
        private LifecycleFilter currentFilter;
        private List<LifecycleFilterPredicate> andOperandsList;
        private String currentTagKey;
        private String currentTagValue;

        public BucketLifecycleConfiguration getConfiguration() {
            return configuration;
        }

        @Override
        protected void doStartElement(
                String uri,
                String name,
                String qName,
                Attributes attrs) {

            if (in("LifecycleConfiguration")) {
                if (name.equals("Rule")) {
                    currentRule = new Rule();
                }
            } else if (in("LifecycleConfiguration", "Rule")) {
                if (name.equals("Transition")) {
                    currentTransition = new Transition();
                } else if (name.equals("NoncurrentVersionTransition")) {
                    currentNcvTransition = new NoncurrentVersionTransition();
                } else if (name.equals("AbortIncompleteMultipartUpload")) {
                    abortIncompleteMultipartUpload = new
                            AbortIncompleteMultipartUpload();
                } else if (name.equals("Filter")) {
                    currentFilter = new LifecycleFilter();
                }
            } else if (in("LifecycleConfiguration", "Rule", "Filter")) {
                if (name.equals("And")) {
                    andOperandsList = new ArrayList<LifecycleFilterPredicate>();
                }
            }
        }

        @Override
        protected void doEndElement(String uri, String name, String qName) {
            if (in("LifecycleConfiguration")) {
                if (name.equals("Rule")) {
                    configuration.getRules().add(currentRule);
                    currentRule = null;
                }
            }

            else if (in("LifecycleConfiguration", "Rule")) {
                if ( name.equals("ID") ) {
                    currentRule.setId(getText());

                } else if ( name.equals("Prefix") ) {
                    currentRule.setPrefix(getText());

                } else if ( name.equals("Status") ) {
                    currentRule.setStatus(getText());

                } else if (name.equals("Transition")) {
                    currentRule.addTransition(currentTransition);
                    currentTransition = null;

                } else if (name.equals("NoncurrentVersionTransition")) {
                    currentRule.addNoncurrentVersionTransition(
                            currentNcvTransition);
                    currentNcvTransition = null;
                } else if (name.equals("AbortIncompleteMultipartUpload")) {
                    currentRule.setAbortIncompleteMultipartUpload(abortIncompleteMultipartUpload);
                    abortIncompleteMultipartUpload = null;
                } else if (name.equals("Filter")) {
                    currentRule.setFilter(currentFilter);
                    currentFilter = null;
                }
            }

            else if (in("LifecycleConfiguration", "Rule", "Expiration")) {
                if (name.equals("Date")) {
                    currentRule.setExpirationDate(ServiceUtils.parseIso8601Date(getText()));
                } else if (name.equals("Days")) {
                    currentRule.setExpirationInDays(Integer.parseInt(getText()));
                } else if (name.equals("ExpiredObjectDeleteMarker")) {
                    if ("true".equals(getText())) {
                        currentRule.setExpiredObjectDeleteMarker(true);
                    }
                }
            }

            else if (in("LifecycleConfiguration", "Rule", "Transition")) {
                if (name.equals("StorageClass")) {
                    currentTransition.setStorageClass(getText());
                } else if (name.equals("Date")) {
                    currentTransition.setDate(
                            ServiceUtils.parseIso8601Date(getText()));

                } else if (name.equals("Days")) {
                    currentTransition.setDays(Integer.parseInt(getText()));
                }
            }

            else if (in("LifecycleConfiguration", "Rule", "NoncurrentVersionExpiration")) {
                if (name.equals("NoncurrentDays")) {
                    currentRule.setNoncurrentVersionExpirationInDays(
                            Integer.parseInt(getText()));
                }
            }

            else if (in("LifecycleConfiguration", "Rule", "NoncurrentVersionTransition")) {
                if (name.equals("StorageClass")) {
                    currentNcvTransition.setStorageClass(getText());
                } else if (name.equals("NoncurrentDays")) {
                    currentNcvTransition.setDays(Integer.parseInt(getText()));
                }
            }

            else if (in("LifecycleConfiguration", "Rule", "AbortIncompleteMultipartUpload")) {
                if (name.equals("DaysAfterInitiation")) {
                    abortIncompleteMultipartUpload.setDaysAfterInitiation
                            (Integer.parseInt(getText()));
                }
            }

            else if (in("LifecycleConfiguration", "Rule", "Filter")) {
                if (name.equals("Prefix")) {
                    currentFilter.setPredicate(new LifecyclePrefixPredicate(getText()));
                } else if (name.equals("Tag")) {
                    currentFilter.setPredicate(new LifecycleTagPredicate(new Tag(currentTagKey, currentTagValue)));
                    currentTagKey = null;
                    currentTagValue = null;
                } else if (name.equals("And")) {
                    currentFilter.setPredicate(new LifecycleAndOperator(andOperandsList));
                    andOperandsList = null;
                }
            }

            else if (in("LifecycleConfiguration", "Rule", "Filter", "Tag")) {
                if (name.equals("Key")) {
                    currentTagKey = getText();
                } else if (name.equals("Value")) {
                    currentTagValue = getText();
                }
            }

            else if (in("LifecycleConfiguration", "Rule", "Filter", "And")) {
                if (name.equals("Prefix")) {
                    andOperandsList.add(new LifecyclePrefixPredicate(getText()));
                } else if (name.equals("Tag")) {
                    andOperandsList.add(new LifecycleTagPredicate(new Tag(currentTagKey, currentTagValue)));
                    currentTagKey = null;
                    currentTagValue = null;
                }
            }

            else if (in("LifecycleConfiguration", "Rule", "Filter", "And", "Tag")) {
                if (name.equals("Key")) {
                    currentTagKey = getText();
                } else if (name.equals("Value")) {
                    currentTagValue = getText();
                }
            }

        }
    }
